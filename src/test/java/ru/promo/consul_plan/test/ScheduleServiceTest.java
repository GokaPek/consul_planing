package ru.promo.consul_plan.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.test.context.SpringBootTest;
import ru.promo.consul_plan.dto.ScheduleDTO;
import ru.promo.consul_plan.entity.ScheduleEntity;
import ru.promo.consul_plan.entity.SpecialistEntity;
import ru.promo.consul_plan.repository.ScheduleRepository;
import ru.promo.consul_plan.repository.SpecialistRepository;
import ru.promo.consul_plan.service.ScheduleService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WavefrontProperties.Application.class) // Укажите ваш класс конфигурации Spring Boot
@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private SpecialistRepository specialistRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    private ScheduleDTO scheduleDTO;
    private SpecialistEntity specialistEntity;
    private ScheduleEntity scheduleEntity;

    @BeforeEach
    public void setUp() {
        Long specialistId = 1L;
        specialistEntity = new SpecialistEntity();
        specialistEntity.setId(specialistId);

        scheduleDTO = new ScheduleDTO();
        scheduleDTO.setSpecialistId(specialistId);
        scheduleDTO.setDate(LocalDate.of(2024, 10, 13));
        scheduleDTO.setStartTime(String.valueOf(LocalTime.of(9, 0)));
        scheduleDTO.setEndTime(String.valueOf(LocalTime.of(10, 0)));

        scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(1L);
        scheduleEntity.setSpecialist(specialistEntity);
        scheduleEntity.setDate(scheduleDTO.getDate());
        scheduleEntity.setStartTime(LocalTime.parse(scheduleDTO.getStartTime()));
        scheduleEntity.setEndTime(LocalTime.parse(scheduleDTO.getEndTime()));
    }

    @Test
    public void testCreateSchedule() {
        when(specialistRepository.findById(1L)).thenReturn(Optional.of(specialistEntity));
        when(scheduleRepository.save(any(ScheduleEntity.class))).thenReturn(scheduleEntity);

        scheduleService.create(scheduleDTO);

        verify(specialistRepository, times(1)).findById(scheduleDTO.getSpecialistId());
        verify(scheduleRepository, times(1)).save(any(ScheduleEntity.class));
    }

    @Test
    public void testGetById() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(scheduleEntity));

        ScheduleEntity result = scheduleService.getById(1L);

        assertNotNull(result, "ScheduleEntity should not be null");
        assertEquals(scheduleEntity.getId(), result.getId());
        assertEquals(scheduleEntity.getSpecialist().getId(), result.getSpecialist().getId());
        assertEquals(scheduleEntity.getDate(), result.getDate());
        assertEquals(scheduleEntity.getStartTime(), result.getStartTime());
        assertEquals(scheduleEntity.getEndTime(), result.getEndTime());
    }

    @Test
    public void testUpdateSchedule() {
        when(scheduleRepository.existsById(1L)).thenReturn(true);
        when(specialistRepository.findById(1L)).thenReturn(Optional.of(specialistEntity));
        when(scheduleRepository.save(any(ScheduleEntity.class))).thenReturn(scheduleEntity);

        scheduleDTO.setId(1L);
        scheduleService.update(scheduleDTO);

        verify(scheduleRepository, times(1)).existsById(scheduleDTO.getId());
        verify(specialistRepository, times(1)).findById(scheduleDTO.getSpecialistId());
        verify(scheduleRepository, times(1)).save(any(ScheduleEntity.class));
    }

    @Test
    public void testDeleteSchedule() {
        doNothing().when(scheduleRepository).deleteById(1L);

        scheduleService.delete(1L);

        verify(scheduleRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllBySpecialistId() {
        when(scheduleRepository.findAllBySpecialistId(1L)).thenReturn(List.of(scheduleEntity));

        List<ScheduleEntity> result = scheduleService.getAllBySpecialistId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(scheduleEntity.getId(), result.get(0).getId());
    }
}