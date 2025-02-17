package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.exception.NotFoundException;
import ru.promo.consul_plan.mapper.ScheduleEntityMapper;
import ru.promo.consul_plan.mapper.ScheduleMapper;
import ru.promo.consul_plan.repository.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private static final String CACHE_PREFIX = "schedule_";
    private final ScheduleRepository scheduleRepository;
    private final SpecialistService specialistService;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleEntityMapper scheduleEntityMapper;
    private final RedisTemplate<String, Schedule> redisTemplate;

    @Override
    public void create(Schedule dto) {
        ScheduleEntity entity = scheduleEntityMapper.toEntity(dto);
        ScheduleEntity savedEntity = scheduleRepository.save(entity);
        dto.setId(savedEntity.getId());
        saveToRedis(CACHE_PREFIX + savedEntity.getId(), dto);
    }

    @Override
    public Schedule getDTOById(Long id) {
        String key = CACHE_PREFIX + id;
        Schedule cachedSchedule = getFromRedis(key);
        if (cachedSchedule != null) {
            return cachedSchedule;
        }
        return scheduleRepository.findById(id)
                .map(scheduleEntity -> {
                    Schedule schedule = scheduleMapper.toDTO(scheduleEntity);
                    saveToRedis(CACHE_PREFIX + id, schedule);
                    return schedule;
                })
                .orElseThrow(() -> new NotFoundException("Schedule not found with id: " + id));
    }

    @Override
    public Optional<ScheduleEntity> getById(Long id) {
        return scheduleRepository.findById(id);
    }


    @Override
    public void update(Schedule dto) {
        if (!scheduleRepository.existsById(dto.getId())) {
            throw new NotFoundException("Schedule not found with id: " + dto.getId());
        }

        ScheduleEntity existingEntity = scheduleRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Schedule not found with id: " + dto.getId()));

        SpecialistEntity specialist = specialistService.getById(dto.getSpecialistId());
        existingEntity.setSpecialist(specialist);
        existingEntity.setStartTime(dto.getStartTime());
        existingEntity.setEndTime(dto.getEndTime());

        scheduleRepository.save(existingEntity);
        saveToRedis(CACHE_PREFIX + dto.getId(), dto);
    }

    @Override
    public void update(ScheduleEntity entity) {
        if (scheduleRepository.existsById(entity.getId())) {
            scheduleRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
        deleteFromRedis(CACHE_PREFIX + id);
    }

    @Override
    public List<Schedule> getAllBySpecialistId(Long specialistId) {
        return scheduleMapper.toDTOlist(scheduleRepository.findAllBySpecialistIdAndClientIsNull(specialistId));
    }
    @Override
    @Transactional
    public List<Schedule> getAll() {
        return scheduleMapper.toDTOlist(scheduleRepository.findAll());
    }

    @Override
    public List<Schedule> findAllByDateTimeBetween(LocalDate localDate) {
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);

        return scheduleMapper.toDTOlist(scheduleRepository.findAllByStartTimeBetween(startOfDay, endOfDay));
    }

    private Schedule getFromRedis(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("Error reading from Redis with key '{}': {}", key, e.getMessage(), e);
            return null;
        }
    }

    private void saveToRedis(String key, Schedule schedule) {
        try {
            redisTemplate.opsForValue().set(key, schedule, 1, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error("Error saving to Redis with key '{}': {}", key, e.getMessage(), e);
        }
    }

    private void deleteFromRedis(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("Error deleting from Redis with key '{}': {}", key, e.getMessage(), e);
        }
    }
}