package co.edu.mintic.ciclo4.minticciclo4.service;

import co.edu.mintic.ciclo4.minticciclo4.model.Order;
import co.edu.mintic.ciclo4.minticciclo4.model.User;
import co.edu.mintic.ciclo4.minticciclo4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {

        var it = repository.findAll();
        var users = new ArrayList<User>();
        it.forEach(client -> users.add(client));
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            return repository.save(user);
        } else {
            Optional<User> result = repository.findById(user.getId().toString());
            if(result.isEmpty()) {
                return repository.save(user);
            } else {
                return user;
            }
        }
    }

    public boolean isEmailPresent(String email) {

        List<User> users = repository.getUserByEmail(email);

        if( users.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {

        Optional<User> user = repository.getUserByEmailAndPassword(email, password);

        if (!user.isPresent()) {

            Optional<User> newUser = Optional.of(new User());
            return newUser;
        } else {
            return user;
        }
    }

    public User update(User user) {

        if(user.getId() == null) {
            return repository.save(user);
        } else {
            List<User> result = repository.getUserById(user.getId());
            if(result.size() != 0) {

                User existing = result.get(0);
                existing.setIdentification(user.getIdentification());
                existing.setName(user.getName());
                existing.setBirthtDay(user.getBirthtDay());
                existing.setMonthBirthtDay(user.getMonthBirthtDay());
                existing.setAddress(user.getAddress());
                existing.setCellPhone(user.getCellPhone());
                existing.setEmail(user.getEmail());
                existing.setPassword(user.getPassword());
                existing.setZone(user.getZone());
                existing.setType(user.getType());

                return repository.save(existing);
            } else {
                return user;
            }
        }
    }

    public boolean delete(Integer id) {

        List<User> users = repository.getUserById(id);
        if(users.size() != 0) {
            repository.delete(users.get(0));
            return true;
        } else {
            return false;
        }
    }

    public User getUserById(Integer id) {

        List<User> users = repository.getUserById(id);
        return users.get(0);
    }

    public List<User> birthtDayList(String monthBirthtDay) {
        return repository.birthtDayList(monthBirthtDay);
    }

}
