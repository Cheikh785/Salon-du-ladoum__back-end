package com.example.projetladoum.Web.Controller;

import com.example.projetladoum.Models.Jury;
import com.example.projetladoum.Services.JuryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin("")
public class JuryController {
    @Autowired
    private JuryService juryService;

    //Get all Jury
    @GetMapping(value = "/Jury")
    public List<Jury> getJury() {
        return juryService.getAllJury();
    }

    //Get jury by id
    @GetMapping(value = "/Jury/{id}")
    public ResponseEntity<Jury> getJuryById(@PathVariable int id) {
        Jury jury = juryService.getJuryById(id);

        if (jury == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(jury);
    }

    //Get jury by login
    @GetMapping(value = "/Jury/login/{login}")
    public ResponseEntity<Jury> getJuryByLogin(@PathVariable String login) {
        Jury jury = juryService.getJuryByLogin(login);

        if(jury == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(jury);
    }

    //Get jury by email
    @GetMapping(value = "/Jury/email/{email}")
    public ResponseEntity<Jury> getJuryByEmail(@PathVariable String email) {
        Jury jury = juryService.getJuryByEmail(email);

        if(jury == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(jury);
    }

    //Add a jury
    @PostMapping("/Jury")
    public ResponseEntity<Jury> addJury(@RequestBody Jury jury) {
        Jury juryAdd = juryService.registerJury(jury);

        if (juryAdd == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(juryAdd.getId())
                .toUri();
        return ResponseEntity.created(location).body(jury);
    }

    //Update Jury
    @PutMapping(value = "/Jury/{id}")
    public ResponseEntity<Jury> updateJury(@PathVariable int id, @RequestBody Jury juryToUpdate) {
        Jury jury = juryService.updateJury(juryToUpdate, id);

        if (jury != null)
            return ResponseEntity.ok().body(jury);

        return ResponseEntity.notFound().build();
    }

    //Delete Jury
    @DeleteMapping("/Jury/{id}")
    public ResponseEntity<Void> deleteJury(@PathVariable int id) {
        int idJuryToDelete = juryService.deleteJury(id);

        if (idJuryToDelete == -1)
            return ResponseEntity.notFound().build();

        return new ResponseEntity("Jury id deleted successfuly !", HttpStatus.OK);
    }
}






















