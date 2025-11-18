package com.skillstorm.springsecurity.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class AppUser implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_role")
    private String role;            // USER, ADMIN, MOD, etc.

    public AppUser() {
        
    }

    public AppUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AppUser(long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * below methods are inherited from UserDetails
     */

    @Override       // getter method for roles
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * returns a collection of any objects that extend the GrantedAuthority class
         * 
         *      we will use SimpleGrantedAuthority
         */

        /**
         * in spring security, any text can be recognized as an authority
         * but roles specifically need to be in the format of ROLE_*
         * 
         *      just going to transalte our role field into something spring security will recognize
         */
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(role);
        authorities.add(userRole);

        return authorities;     // USER -> ROLE_USER
    }

    @Override   // getter method for password
    public String getPassword() {
        return this.password;
    }

    @Override   // getter method for username
    public String getUsername() {
        return this.username;
    }


    /**
     * these boolean methods can be used to manage your users
     *      if any of them return false, the user will not be able to log in
     */

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
