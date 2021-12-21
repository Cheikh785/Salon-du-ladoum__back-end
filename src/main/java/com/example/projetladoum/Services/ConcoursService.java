package com.example.projetladoum.Services;

import com.example.projetladoum.Models.Concours;
import com.example.projetladoum.dao.ConcoursDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcoursService {

    @Autowired
    private ConcoursDao concoursDao;

    public List<Concours> getAllConcours() {
        return this.concoursDao.findAll();
    }

    public Concours getConcoursById(int id) {
        return this.concoursDao.findConcoursById(id);
    }

    public Concours registerConcours(Concours concours) {
        if (concours == null)
            return null;
        return this.concoursDao.save(concours);
    }

    public Concours updateConcours(Concours concours, int id) {
        Concours existingConcours = this.concoursDao.findConcoursById(id);
        if (existingConcours != null) {

            if (concours.getEdition() != 0)
                existingConcours.setEdition(concours.getEdition());

            if (concours.getDate() != null)
                existingConcours.setDate(concours.getDate());

            if (concours.getLieu() != null)
                existingConcours.setLieu(concours.getLieu());

            return this.concoursDao.save(existingConcours);
        }
        return null;
    }
}
