package fr.rbo.elitapi.controller;

import fr.rbo.elitapi.entity.Ouvrage;
import fr.rbo.elitapi.exceptions.NotAcceptableException;
import fr.rbo.elitapi.exceptions.NotFoundException;
import fr.rbo.elitapi.repository.OuvrageRepository;
import fr.rbo.elitapi.repository.OuvrageRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OuvrageController {
    @Autowired
    OuvrageRepository ouvrageRepository;
    @Autowired
    OuvrageRepositoryInterface ouvrageRecherche;

    @GetMapping(value="/ouvrages")
    public List<Ouvrage> listeDesOuvrages(){
        List<Ouvrage> ouvrages = ouvrageRepository.findAll();
        if (ouvrages.isEmpty()) throw new NotFoundException("Il n'y a pas d'ouvrage correspondant à votre recherche");
        return ouvrages;
    }
    @PostMapping(value="/ouvrages/recherche")
    public List<Ouvrage> listeDesOuvragesSelonCriteres( @RequestBody Ouvrage ouvrageCherche){
        List<Ouvrage> ouvrages = ouvrageRecherche.rechercheOuvrage(ouvrageCherche);
        if (ouvrages.isEmpty()) throw new NotFoundException("Il n'y a pas d'ouvrage correspondant à votre recherche");
        return ouvrages;
    }
    @GetMapping(value = "/ouvrage/{id}")
    public Ouvrage recupererUnOuvrage (@PathVariable("id") Long id){
        Ouvrage ouvrage = ouvrageRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Cet ouvrage n'existe pas"));
        return ouvrage;
    }

    @PostMapping(value = "/ouvrage/ajout")
    public Ouvrage creerOuvrage (@RequestBody Ouvrage ouvrage){
        if (ouvrage.getOuvrageId() != null) throw new NotAcceptableException("Demande fausse, ouvrageId présent");
        ouvrageRepository.save(ouvrage);
        return ouvrage;
    }

    @PutMapping(value = "/ouvrage/maj/{id}")
    public Ouvrage mettreAJourOuvrage (@PathVariable("id") Long id,
                                        @RequestBody Ouvrage ouvrageNew){
        if (ouvrageNew.getOuvrageId() != null) {
            if (!ouvrageNew.getOuvrageId().equals(id)) throw new NotAcceptableException("Demande fausse, ouvrageId différent");
        }
        Ouvrage ouvrage = ouvrageRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Ouvrage inexistant, non trouvé"));
        if (ouvrageNew.getOuvrageReference() != null) ouvrage.setOuvrageReference(ouvrageNew.getOuvrageReference());
        if (ouvrageNew.getOuvrageTitre() != null) ouvrage.setOuvrageTitre(ouvrageNew.getOuvrageTitre());
        if (ouvrageNew.getOuvrageAuteur() != null) ouvrage.setOuvrageAuteur(ouvrageNew.getOuvrageAuteur());
        if (ouvrageNew.getOuvrageStyle() != null) ouvrage.setOuvrageStyle(ouvrageNew.getOuvrageStyle());
        if (ouvrageNew.getOuvrageResume() != null) ouvrage.setOuvrageResume(ouvrageNew.getOuvrageResume());
        if (ouvrageNew.getOuvrageLocalisation() != null) ouvrage.setOuvrageLocalisation(ouvrageNew.getOuvrageLocalisation());
        if (ouvrageNew.getOuvrageQuantite() != null) ouvrage.setOuvrageQuantite(ouvrageNew.getOuvrageQuantite());
        ouvrageRepository.save(ouvrage);
        return ouvrage;
    }

}
