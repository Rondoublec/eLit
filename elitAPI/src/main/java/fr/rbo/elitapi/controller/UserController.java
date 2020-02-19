package fr.rbo.elitapi.controller;

import fr.rbo.elitapi.entity.User;
import fr.rbo.elitapi.exceptions.NotFoundException;
import fr.rbo.elitapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/users")
    public List<User> listeDesUsers(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new NotFoundException("Aucun utilisateur présent");
        return users;
    }

    @GetMapping(value = "/user/{id}")
    public User recupererUser (@PathVariable("id") Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Utilisateur inexistant"));
        return user;
    }

    @GetMapping(value = "/user/email/{email}")
    public User recupererUserParEmail (@PathVariable("email") String email){
        User user = userRepository.findByEmail(email);
        if (user == null) throw new NotFoundException("Email utilisateur inexistant");
        return user;
    }

    @PostMapping(value = "/user/")
    @ResponseBody
    public User creerUser (@RequestBody User user){
        User newUser = userRepository.findById(user.getId()).orElse(new User());
        majUser(user, newUser);
        userRepository.save(newUser);
        user = newUser;
        return user;
    }

    private void majUser(User userSource, User userCible) {
        userCible.setEmail(userSource.getEmail());
        userCible.setName(userSource.getName());
        userCible.setLastName(userSource.getLastName());
        userCible.setPassword(userSource.getPassword());
        userCible.setRoles(userSource.getRoles());
        userCible.setActive(userSource.isActive());
    }
}
