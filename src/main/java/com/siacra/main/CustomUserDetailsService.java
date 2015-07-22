package com.siacra.main;


import com.siacra.daos.UserDao;
import com.siacra.models.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * CustomUserDetailsService
 *
 * @author SIACRA Development Team
 * @since 16-07-15
 * @version 1.0.0
 *
 */

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired
    private UserDao userDAO;   
    
    /**
     * Load User by Username
     *
     * @param  login String
     * @return UserDetails
     */
    
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
       
        User domainUser = userDAO.getUserLogin(login);
        boolean enabled = false;
        boolean accountNonLocked = false;
        boolean accountNonExpired = false;
        boolean credentialsNonExpired = false;
        if(domainUser.getEstadoUsuario() == 1){
            enabled = true;
            accountNonLocked = true;
            accountNonExpired = true;
            credentialsNonExpired = true;
        }    
        
        return new org.springframework.security.core.userdetails.User(
            domainUser.getNombreUsuario(),
            domainUser.getContrasenia(),
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            getAuthorities(domainUser.getNivel().getId())
        );
    }
    
    /**
     * Load Get Authorities
     *
     * @param  role Integer
     * @return GrantedAuthority Collection - Granted Authorization Collection
     */
    
    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }
    
    /**
     * Load Get Roles
     *
     * @param  role Integer
     * @return String List - Roles List
     */
    
    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<String>();

        if (role.intValue() == 1) {
            roles.add("ADMINISTRADOR");
        } else if (role.intValue() == 2) {
            roles.add("RESPONSABLE");
        } else if (role.intValue() == 3) {
            roles.add("DIRECTOR");
        } else if (role.intValue() == 4) {
            roles.add("MIEMBRO_JD");
        } else if (role.intValue() == 5) {
            roles.add("DOCENTE");
        }
        return roles;
    }
    
    /**
     * Load User by Username
     *
     * @param  roles String List
     * @return GrantedAuthority List - Authorization List
     */
    
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
