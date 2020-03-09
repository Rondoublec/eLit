package fr.rbo.elitapi.controller;

import fr.rbo.elitapi.entity.Emprunt;
import fr.rbo.elitapi.entity.Ouvrage;
import fr.rbo.elitapi.entity.User;
import fr.rbo.elitapi.exceptions.NotAcceptableException;
import fr.rbo.elitapi.exceptions.NotFoundException;
import fr.rbo.elitapi.repository.EmpruntRepository;
import fr.rbo.elitapi.repository.EmpruntRepositoryInterface;
import fr.rbo.elitapi.repository.OuvrageRepository;
import fr.rbo.elitapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpruntController {

    @Autowired
    EmpruntRepository empruntRepository;
    @Autowired
    EmpruntRepositoryInterface empruntRecherche;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OuvrageRepository ouvrageRepository;

    @GetMapping(value="/emprunts")
    public List<Emprunt> listeDesEmprunts(){
        List<Emprunt> emprunts = empruntRepository.findAll();
        if (emprunts.isEmpty()) throw new NotFoundException("Il n'y a pas d'emprunt correspondant à votre recherche");
        return emprunts;
    }

    @PostMapping(value="/emprunts/recherche/{action}")
    public List<Emprunt> listeDesEmpruntsSelonCriteres( @PathVariable("action") String action
            , @RequestBody Emprunt empruntCherche){
        List<Emprunt> emprunts = empruntRecherche.rechercheEmprunt(empruntCherche, action);
        if (emprunts.isEmpty()) throw new NotFoundException("Il n'y a pas d'ouvrage correspondant à votre recherche");
        return emprunts;
    }

    @GetMapping(value = "/emprunt/{id}")
    public Optional<Emprunt> recupererUnEmprunt (@PathVariable("id") Long id){
        Optional<Emprunt> emprunt = empruntRepository.findById(id);
        if (!emprunt.isPresent()) throw new NotFoundException("Cet emprunt n'existe pas");
        return emprunt;
    }

    @PutMapping(value = "/emprunt/plus/{id}")
    public Emprunt prolongerEmprunt (@PathVariable("id") Long id){
        Emprunt emprunt = empruntRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Emprunt inexistant, non trouvé"));
        if (null != emprunt.getEmpruntDateProlongation()) {
            throw new NotAcceptableException("Prolongation impossible, emprunt déjà prolongé une fois");
        }
        if (null != emprunt.getEmpruntDateRetour()) {
            throw new NotAcceptableException("Prolongation impossible, ouvrage déjà rendu");
        }
        emprunt.setEmpruntDateProlongation(dateFinPeriode(emprunt.getEmpruntDateFin(),28));
        emprunt.setEmpruntProlongation(true);
        empruntRepository.save(emprunt);
        return emprunt;
    }

    @PostMapping(value = "/emprunt/ajout")
    public Emprunt creerEmprunt (@RequestBody Emprunt emprunt){
        Ouvrage ouvrage = ouvrageRepository.findById(emprunt.getOuvrage().getOuvrageId()).orElseThrow(() ->
                new NotFoundException("Ouvrage inconnu"));
        if (Integer.parseInt(ouvrage.getOuvrageQuantite() )< 1) {
            throw new NotAcceptableException("Emprunt impossible, ouvrage non disponible");
        }
        User user = userRepository.findById(emprunt.getUser().getId()).orElseThrow(() ->
                new NotFoundException("Utilisateur inconnu"));
        // Vérifs OK, mise à jour de la quantité d'ouvrages restante avant de créer l'emprunt TODO gérer le rollback
        Integer qte = Integer.parseInt(ouvrage.getOuvrageQuantite())-1;
        ouvrage.setOuvrageQuantite(qte.toString());
        ouvrageRepository.save(ouvrage);
        emprunt.setUser(user);
        emprunt.setOuvrage(ouvrage);
        emprunt.setEmpruntDateDebut(Calendar.getInstance().getTime());
        emprunt.setEmpruntDateFin(dateFinPeriode(emprunt.getEmpruntDateDebut(),28));
        emprunt.setEmpruntProlongation(false);
        emprunt.setEmpruntRelance(false);
        emprunt.setEmpruntRendu(false);
        empruntRepository.save(emprunt);
        return emprunt;
    }
    @PostMapping(value = "/emprunt/upsert")
    public Emprunt majOrUpdateEmprunt (@RequestBody Emprunt emprunt){
        emprunt.setEmpruntDateDebut(Calendar.getInstance().getTime());
        emprunt.setEmpruntDateFin(dateFinPeriode(emprunt.getEmpruntDateDebut(),28));
        empruntRepository.save(emprunt);
        return emprunt;
    }


    @GetMapping(value = "/emprunt/retour/{id}")
    public Emprunt retournerEmprunt (@PathVariable("id") Long id){
        Emprunt emprunt = empruntRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Emprunt inexistant, non trouvé"));
        if (null != emprunt.getEmpruntDateRetour()) {
            throw new NotAcceptableException("Demande éronnée, ouvrage déjà rendu");
        }
        Ouvrage ouvrage = ouvrageRepository.findById(emprunt.getOuvrage().getOuvrageId()).orElseThrow(() ->
                new NotFoundException("Ouvrage inconnu"));
        // Vérifs OK, mise à jour de la quantité d'ouvrages restante avant de mettre à jour l'emprunt TODO gérer le rollback
        Integer qte = Integer.parseInt(ouvrage.getOuvrageQuantite())+1;
        ouvrage.setOuvrageQuantite(qte.toString());
        ouvrageRepository.save(ouvrage);
        emprunt.setEmpruntDateRetour(Calendar.getInstance().getTime());
        emprunt.setEmpruntRendu(true);
        empruntRepository.save(emprunt);
        return emprunt;
    }

    @GetMapping(value = "/emprunt/suppr/{id}")
    public String supprimerEmprunt (@PathVariable("id") Long id){
        Optional<Emprunt> emprunt = empruntRepository.findById(id);
        if (emprunt.isPresent()) {
            empruntRepository.deleteById(id);
        } else {
            return "Demande éronnée, emprunt inexistant";
        }
        emprunt = empruntRepository.findById(id);
        if (emprunt.isPresent()) {
            return "KO : Suppression echouée";
        } else {
            return "OK : Suppression réussie";
        }
    }

    @GetMapping(value="/emprunts/enretard/{id}")
    public List<Emprunt> listeDesEmpruntsEnRetard(@PathVariable("id") long id) {
        User userRecherche = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Utilisateur inexistant"));
        List<Emprunt> emprunts = empruntRepository.findAllByUserAndEmpruntDateFinIsBeforeAndEmpruntDateProlongationIsNullAndEmpruntDateRetourIsNullOrEmpruntDateProlongationIsNotNullAndEmpruntDateProlongationIsBeforeAndEmpruntDateRetourIsNull(userRecherche, new Date(), new Date());
        if (emprunts.isEmpty()) throw new NotFoundException("Aucun emprunt en retard");
        return emprunts;
    }

    private Date dateFinPeriode (Date dateDebut, int duree){
        GregorianCalendar dateFin = new GregorianCalendar();
        dateFin.setTime(dateDebut);
        dateFin.add(GregorianCalendar.DATE,duree);
        return dateFin.getTime();
    }

}
