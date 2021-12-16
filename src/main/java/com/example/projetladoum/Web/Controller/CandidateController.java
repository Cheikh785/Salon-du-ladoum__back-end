package com.example.projetladoum.Web.Controller;

import com.example.projetladoum.Models.Candidate;
import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    //Get all candidate
    @GetMapping(value = "/candidate")
    public List<Candidate> getAllCandidate() {
        return candidateService.getAllCandidate();
    }

    //Get a candidate by her id
    @GetMapping(value = "/candidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable int id) {
        Candidate candidate = candidateService.getCandidateById(id);

        if (candidate == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(candidate);
    }

    //Register a candidate
    @PostMapping(value = "/candidate")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody Candidate candidate) {

        Candidate candidate1 = candidateService.registerCandidate(candidate);

        if (candidate1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(candidate1.getId())
                .toUri();
        return ResponseEntity.created(location).body(candidate);
    }

    //Update a candidate
    @PutMapping(value = "/candidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidateToUpdate, @PathVariable int id) {
        Candidate candidateUpdated = candidateService.updateCandidate(candidateToUpdate, id);

        if (candidateUpdated != null)
            return ResponseEntity.ok().body(candidateUpdated);

        return ResponseEntity.notFound().build();
    }

    //Delete a candidate
    @DeleteMapping(value = "/candidate/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable int id) {
        int idCandidateToDelete = candidateService.deleteCandidate(id);

        if (idCandidateToDelete == -1)
            return ResponseEntity.notFound().build();

        return new ResponseEntity("The candidate is deleted successfuly !", HttpStatus.OK);
    }

    //Get candidate by type of ovin
    @GetMapping(value = "/candidate/type/{type}")
    public ResponseEntity<List<Object[]>> getCandidateByTypeOvin(@PathVariable String type) {
        List<Object[]> candidates = candidateService.getCandidateByTypeOvin(type);

        if (candidates != null || !candidates.isEmpty())
            return ResponseEntity.ok().body(candidates);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/candidate/mesureOvinOfCandidate/{id}")
    public ResponseEntity<Mesures> getMesureOfOvinOfCandidate(@PathVariable int id) {
        Mesures mesure = candidateService.getMesureOfOvinOfCandidate(id);

        if (mesure == null)
            return null;
        return ResponseEntity.ok().body(mesure);
    }
}
