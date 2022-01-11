package co.edu.mintic.ciclo4.minticciclo4.controller;

import co.edu.mintic.ciclo4.minticciclo4.kafka.Producer;
import co.edu.mintic.ciclo4.minticciclo4.model.User;
import co.edu.mintic.ciclo4.minticciclo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class UserController {

    private Producer producer = new Producer();

    @Autowired
    private UserService service;

    @GetMapping("/all")
    public List<User> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return service.getUserById(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {

        return service.save(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user) {

        return service.update(user);
    }

    @GetMapping("/emailexist/{email}")
    public boolean isEmailPresent(@PathVariable("email") String email) {

        return service.isEmailPresent(email);
    }

    @GetMapping("/{email}/{password}")
    public Optional<User> getUserByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        producer.run(email);
        return service.getUserByEmailAndPassword(email, password);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer id) { return service.delete(id); }

    @GetMapping("/birthday/{month}")
    public List<User> birthtDayList(@PathVariable("month") String monthBirthtDay) {
        return service.birthtDayList(monthBirthtDay);
    }
}
