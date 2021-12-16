package com.example.projetladoum.Web.Controller;

import com.example.projetladoum.Models.Candidate;
import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Models.Ovins;
import com.example.projetladoum.Services.OvinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OvinsController {

    @Autowired
    private OvinsService ovinsService;

    @GetMapping(value = "/ovins")
    public List<Ovins> getAllOvins() {
        return ovinsService.getAllOvins();
    }



    @GetMapping(value = "/ovins/{id}")
    public ResponseEntity<Ovins> getOvinById(@PathVariable int id) {
        Ovins ovinFound = ovinsService.getOvinsById(id);

        if (ovinFound == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(ovinFound);
    }



    @PostMapping(value = "/ovins")
    public ResponseEntity<Ovins> registerOvin(@RequestBody Ovins ovinToRegister) {
        Ovins ovinRegistered = ovinsService.registerOvin(ovinToRegister);

        if(ovinRegistered == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ovinRegistered.getId())
                .toUri();
        return ResponseEntity.created(location).body(ovinToRegister);
    }



    @PutMapping(value = "/ovins/{id}")
    public ResponseEntity<Ovins> updateOvin(@RequestBody Ovins ovinToUpdate, @PathVariable int id) {
        Ovins ovinUpdated = ovinsService.updateOvin(ovinToUpdate, id);

        if (ovinUpdated != null)
            return ResponseEntity.ok().body(ovinUpdated);

        return ResponseEntity.notFound().build();
    }



    @DeleteMapping(value = "/ovins/{id}")
    public ResponseEntity<Void> deleteOvin(@PathVariable int id) {
        int ovinDeleted = ovinsService.deleteOvin(id);

        if (ovinDeleted == -1)
            return ResponseEntity.notFound().build();

        return new ResponseEntity("The ovin is deleted successfuly !", HttpStatus.OK);
    }


    @GetMapping(value = "/ovins/getRang/{genre}")
    public ResponseEntity<List<Candidate>> getRangByGenre(@PathVariable String genre) {
        List<Candidate> listOvins = ovinsService.getScoreByGenre(genre);

        if (listOvins != null && !listOvins.isEmpty())
            return ResponseEntity.ok().body(listOvins);

        return ResponseEntity.notFound().build();
    }



}










