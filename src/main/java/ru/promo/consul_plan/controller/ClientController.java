package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.entity.ClientEntity;
import ru.promo.consul_plan.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity client) {
        clientService.create(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        ClientEntity client = clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    @PutMapping
    public ResponseEntity<ClientEntity> updateClient(@RequestBody ClientEntity client) {
        clientService.update(client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}