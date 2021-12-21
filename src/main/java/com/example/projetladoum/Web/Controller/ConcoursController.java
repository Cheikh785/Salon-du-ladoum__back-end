package com.example.projetladoum.Web.Controller;


import com.example.projetladoum.Models.Candidate;
import com.example.projetladoum.Models.Concours;
import com.example.projetladoum.Services.ConcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ConcoursController {

    @Autowired
    ConcoursService concoursService;

    @GetMapping(value = "/concours")
    public List<Concours> getAllConcours() {
        return this.concoursService.getAllConcours();
    }


    @GetMapping(value = "/concours/{id}")
    public ResponseEntity<Concours> getConcoursById(@PathVariable int id) {
        Concours concours = concoursService.getConcoursById(id);

        if (concours != null)
            return ResponseEntity.ok().body(concours);

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/concours")
    public ResponseEntity<Concours> registerConcours(@RequestBody Concours concours) {
        Concours concoursRegister = this.concoursService.registerConcours(concours);

        if (concoursRegister == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(concoursRegister.getId())
                .toUri();
        return ResponseEntity.created(location).body(concoursRegister);
    }


    @PutMapping(value = "/concours/{id}")
    public ResponseEntity<Concours> updateConcours(@RequestBody Concours concours, @PathVariable int id) {
        Concours concoursUpdated = this.concoursService.updateConcours(concours, id);

        if (concoursUpdated == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(concoursUpdated);
    }
}
