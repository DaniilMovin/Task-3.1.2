package ru.itmentor.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Component
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "password")
    private String password;


    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {}


    public User(String username, String password, Set<Role> roles) {
        this.name = username;
        this.password = password;
        this.roles = roles;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public String getName() {
        return name;
    }

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