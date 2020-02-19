package fr.clientui.service;

import fr.clientui.beans.RoleBean;
import fr.clientui.beans.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final ClientAPIService clientService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder, ClientAPIService clientService) {
        this.clientService = clientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserBean findUserByEmail(String email) {
        return clientService.recupererUnUserParEmail(email);
    }

    /**
     * Sauvegarde de l'utilisateur / cryptage mdp
     * @param user
     */
    public void saveUser(UserBean user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        HashSet<RoleBean> roles = new HashSet<RoleBean>();
        roles.add(clientService.recupererUnRoleParRole("USER"));
        user.setRoles(roles);
        clientService.creerUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserBean user = clientService.recupererUnUserParEmail(userName);
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<RoleBean> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (RoleBean role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(UserBean user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
    }

}
