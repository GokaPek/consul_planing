package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.entity.SpecialistEntity;
import ru.promo.consul_plan.service.SpecialistService;

@RestController
@RequestMapping("/api/specialists")
public class SpecialistController {

    @Autowired
    private SpecialistService specialistService;

    @PostMapping
    public ResponseEntity<SpecialistEntity> createSpecialist(@RequestBody SpecialistEntity specialist) {
        specialistService.create(specialist);
        return ResponseEntity.ok(specialist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialistEntity> getSpecialistById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        SpecialistEntity specialist = specialistService.getById(id);
        return ResponseEntity.ok(specialist);
    }

    @PutMapping
    public ResponseEntity<SpecialistEntity> updateSpecialist(@RequestBody SpecialistEntity specialist) {
        specialistService.update(specialist);
        return ResponseEntity.ok(specialist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialist(@PathVariable Long id) {
        specialistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}