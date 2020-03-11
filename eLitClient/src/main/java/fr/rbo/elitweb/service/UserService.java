package fr.rbo.elitweb.service;

import fr.rbo.elitweb.beans.UserBean;

public interface UserService {
    /**
     * Recupération de l'utilisateur par son email
     * @param email
     */
    public UserBean findUserByEmail(String email);

    /**
     * Sauvegarde de l'utilisateur / cryptage mdp
     * @param user
     */
    public void saveUser(UserBean user);

}
