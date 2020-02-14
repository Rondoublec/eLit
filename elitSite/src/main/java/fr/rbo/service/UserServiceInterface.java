package fr.rbo.service;

import fr.rbo.model.User;

/**
 * gestion des comptes users
 */
public interface UserServiceInterface {
	/**
	 *
	 * Retourne les informations du compte d'un user correspondant au mail
	 * @param mailUser
	 * @return User
	 */
	public User findUserByEmail(String mailUser);

	/**
	 *
	 * Sauvegarde des informations du compte user
	 * @param user
	 */
	public void saveUser(User user);

}
