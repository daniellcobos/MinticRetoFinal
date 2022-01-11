package co.edu.mintic.ciclo4.minticciclo4.controller;

import co.edu.mintic.ciclo4.minticciclo4.model.Fragance;
import co.edu.mintic.ciclo4.minticciclo4.model.Order;
import co.edu.mintic.ciclo4.minticciclo4.model.User;
import co.edu.mintic.ciclo4.minticciclo4.service.FraganceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fragance")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class FraganceController {

    @Autowired
    private FraganceService service;

    @GetMapping("/all")
    public List<Fragance> getAll() {
        return service.findAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Fragance save(@RequestBody Fragance fragance) {
        return service.save(fragance);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Fragance update(@RequestBody Fragance fragance) {

        return service.update(fragance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") String id) { return service.delete(id); }

    @GetMapping("/{reference}")
    public Optional<Fragance> getFragenceByReference(@PathVariable("reference") String reference) {
        return service.getFraganceByReference(reference);
    }

    @GetMapping("/price/{price}")
    public List<Fragance> gadgetsByPrice(@PathVariable("price") double precio) {
        return service.gadgetsByPrice(precio);
    }

    @GetMapping("/description/{description}")
    public List<Fragance> findByDescriptionLike(@PathVariable("description") String description) {
        return service.findByDescriptionLike(description);
    }
}
