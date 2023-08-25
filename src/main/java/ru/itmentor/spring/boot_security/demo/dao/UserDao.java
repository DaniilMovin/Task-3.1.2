package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;
@Repository
public interface UserDao {
    List<User> getAllUsers();
    void updateUser(User user);
    void removeUser(Long id);
    User getUserById(Long id);
    void save(User user, Set<Long> roleIds);
    User findByUserName(String username);
    Set<Role> findAllRoles();
    Set<Role> findRolesById(Set<Long> roleIds);
}

