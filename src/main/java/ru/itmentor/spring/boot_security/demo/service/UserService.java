package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserDao userDao;
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);

    }

    public void removeUser(Long id) {
        userDao.removeUser(id);

    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }


    public void saveUser(User user, Set<Long> roleIds) {
        userDao.save(user, roleIds);
    }


    public User findByName(String username) {
        return userDao.findByUserName(username);
    }

    public Set<Role> findAllRoles(){
        return userDao.findAllRoles();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByName(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getAuthorities()


        );
    }
}
