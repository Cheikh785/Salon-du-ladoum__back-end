package com.example.projetladoum.Web.Controller;

import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Services.MesuresService;
import com.example.projetladoum.dao.MesuresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
public class MesuresController {

    @Autowired
    private MesuresService mesuresService;

    @Autowired
    private MesuresDao mesuresDao;

    @GetMapping(value = "/mesures")
    public List<Mesures> getAllMesures() {
        return mesuresService.getAllMesures();
    }

    @GetMapping(value = "/mesures/{id}")
    public ResponseEntity<Mesures> getMesureById(@PathVariable int id) {
        Mesures existingMesure = mesuresService.getMesuresById(id);

        if (existingMesure == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(existingMesure);
    }

    @PostMapping(value = "/mesures")
    public ResponseEntity<Mesures> saveMesures(@RequestBody Mesures mesureToSave) {
        Mesures mesureSaved = mesuresService.saveMesures(mesureToSave);

        if (mesureSaved == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mesureSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(mesureSaved);
    }

    @PutMapping(value = "/mesures/{id}")
    public ResponseEntity<Mesures> updateMesures(@RequestBody Mesures mesureToUpdate, @PathVariable int id) {
        Mesures mesureUpdated = mesuresService.updateMesures(mesureToUpdate, id);

        if (mesureUpdated != null)
            return ResponseEntity.ok().body(mesureUpdated);

        return ResponseEntity.notFound().build();
    }



//    @GetMapping(value = "/mesures/greater/{score}")
//    public ResponseEntity<Integer> getMaxHg(@PathVariable double score) {
//        List<Double> listScore = mesuresDao.getScoreByDesc("MaleAdulte");
//
//        List<Integer> listRang = new ArrayList<>();
//        int rang = 1;
//
//        if (!listScore.isEmpty()) {
//            int i=0; int k=1;
//            while (i<listScore.size()-1) {
//                if (listScore.get(i) != listScore.get(i+1)) {
//                    listRang.add(k);
//                    k++;
//                }else {
//                    if (listScore.get(i) == listScore.get(i+1))
//                        listRang.add(k);
//                }
//                i++;
//            }
//            listRang.add(k);
//
//            int j=0;
//            while (j<listScore.size()-1 && listScore.get(j) != score)
//                j=j+1;
//            rang = listRang.get(j);
//        }
//        System.out.println(rang);
//
//        return ResponseEntity.ok().body(rang);
//    }



}










































