package fr.clientui.service;

import fr.clientui.beans.UserBean;

public interface UserService {
    public UserBean findUserByEmail(String email);
    public void saveUser(UserBean user);

}
