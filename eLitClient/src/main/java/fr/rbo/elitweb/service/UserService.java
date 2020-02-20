package fr.rbo.elitweb.service;

import fr.rbo.elitweb.beans.UserBean;

public interface UserService {
    public UserBean findUserByEmail(String email);
    public void saveUser(UserBean user);

}
