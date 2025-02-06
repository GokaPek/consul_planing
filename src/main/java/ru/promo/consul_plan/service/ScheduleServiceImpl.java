package ru.promo.consul_plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.exception.NotFoundException;
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
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void create(Schedule dto) {
        SpecialistEntity specialist = specialistService.getById(dto.getSpecialistId());

        ScheduleEntity entity = new ScheduleEntity();
        entity.setSpecialist(specialist);
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());

        redisTemplate.opsForValue().set(CACHE_PREFIX + dto.getId(), dto, 1, TimeUnit.HOURS);

        scheduleRepository.save(entity);
    }

    private final ObjectMapper objectMapper;

    @Override
    public Schedule getDTOById(Long id) {
        String key = CACHE_PREFIX + id;

        Object cachedSchedule = redisTemplate.opsForValue().get(key);
        if (cachedSchedule != null) {
            try {
                return objectMapper.readValue(objectMapper.writeValueAsBytes(cachedSchedule), Schedule.class);
            } catch (Exception e) {
                log.error("Error deserializing from Redis: {}", e.getMessage(), e);
            }
        }

        ScheduleEntity entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Schedule not found with id: " + id));

        Schedule schedule = scheduleMapper.toDTO(entity);

        redisTemplate.opsForValue().set(key, schedule, 1, TimeUnit.HOURS);

        return schedule;
    }

    @Override
    public Optional<ScheduleEntity> getById(Long id) {
        return scheduleRepository.findById(id);
    }


    @Override
    public void update(Schedule dto) {
        if (scheduleRepository.existsById(dto.getId())) {
            SpecialistEntity specialist = specialistService.getById(dto.getSpecialistId());

            ScheduleEntity entity = new ScheduleEntity();
            entity.setId(dto.getId());
            entity.setSpecialist(specialist);
            entity.setStartTime(dto.getStartTime());
            entity.setEndTime(dto.getEndTime());

            scheduleRepository.save(entity);

            redisTemplate.opsForValue().set(CACHE_PREFIX + dto.getId(), dto, 1, TimeUnit.HOURS);
        }
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

        // Удаляем данные из Redis
        redisTemplate.delete(CACHE_PREFIX + id);
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
}