package fr.rbo.elitapi.controller;

import fr.rbo.elitapi.entity.Bibliotheque;
import fr.rbo.elitapi.entity.Ouvrage;
import fr.rbo.elitapi.exceptions.NotFoundException;
import fr.rbo.elitapi.repository.BibliothequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BibliothequeController {

    @Autowired
    BibliothequeRepository bibliothequeRepository;

    @GetMapping(value="/bibliotheques")
    public List<Bibliotheque> listeDesBibliotheques(){
        List<Bibliotheque> bibliotheques = bibliothequeRepository.findAll();
        if (bibliotheques.isEmpty()) throw new NotFoundException("Il n'y a pas de bibliothèque correspondant à votre recherche");
        return bibliotheques;
    }

}
