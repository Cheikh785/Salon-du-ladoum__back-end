package com.example.projetladoum.Services;

import com.example.projetladoum.Models.Jury;
import com.example.projetladoum.dao.JuryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuryService {

    @Autowired
    private JuryDao juryDao;

    public List<Jury> getAllJury() {
        return juryDao.findAll();
    }

    public Jury getJuryById(int id) {
        Jury juryFound = juryDao.findJuryById(id);

        if (juryFound != null)
            return juryFound;
        else
            return null;
    }

    public Jury getJuryByLogin(String login) {
        Jury juryFound = juryDao.findJuryByLogin(login);

        if (juryFound != null)
            return juryFound;
        else
            return null;
    }

    public Jury getJuryByEmail(String email) {
        Jury juryFound = juryDao.findJuryByEmail(email);

        if (juryFound != null)
            return juryFound;
        else
            return null;
    }

    public Jury registerJury(Jury juryToRegister) {
        return juryDao.save(juryToRegister);
    }

    public Jury updateJury(Jury juryToUpdate, int id) {
        Jury juryFound = getJuryById(id);

        if (juryFound != null) {
            if (juryToUpdate.getLogin() != null && !juryToUpdate.getLogin().isEmpty())
                juryFound.setLogin(juryToUpdate.getLogin());

            if (juryToUpdate.getPassword() != null && !juryToUpdate.getPassword().isEmpty())
                juryFound.setPassword(juryToUpdate.getPassword());

            if (juryToUpdate.getPrenoms() != null && !juryToUpdate.getPrenoms().isEmpty())
                juryFound.setPrenoms(juryToUpdate.getPrenoms());

            if (juryToUpdate.getNom() != null && !juryToUpdate.getNom().isEmpty())
                juryFound.setNom(juryToUpdate.getNom());

            if (juryToUpdate.getBirth() != null)
                juryFound.setBirth(juryToUpdate.getBirth());

            if (juryToUpdate.getRole() != null && !juryToUpdate.getRole().isEmpty())
                juryFound.setRole(juryToUpdate.getRole());

            if (juryToUpdate.getAdresse() != null && !juryToUpdate.getAdresse().isEmpty())
                juryFound.setAdresse(juryToUpdate.getAdresse());

            if (juryToUpdate.getEmail() != null && !juryToUpdate.getEmail().isEmpty())
            juryFound.setEmail(juryToUpdate.getEmail());

            if (juryToUpdate.getTel() != 0)
                juryFound.setTel(juryToUpdate.getTel());

            Jury juryUpdated = juryDao.save(juryFound);
            return juryUpdated;
        } else
            return null;
    }

    public int deleteJury(int id) {
        Jury juryFound = getJuryById(id);

        if (juryFound != null) {
            juryDao.delete(juryFound);
            return id;
        }
        return -1;
    }
}
