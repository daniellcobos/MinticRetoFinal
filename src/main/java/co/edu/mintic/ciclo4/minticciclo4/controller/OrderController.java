package co.edu.mintic.ciclo4.minticciclo4.controller;

import co.edu.mintic.ciclo4.minticciclo4.model.Fragance;
import co.edu.mintic.ciclo4.minticciclo4.model.Order;
import co.edu.mintic.ciclo4.minticciclo4.model.User;
import co.edu.mintic.ciclo4.minticciclo4.service.OrderService;
import co.edu.mintic.ciclo4.minticciclo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/all")
    public List<Order> getAll() {
        return service.findAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order save(@RequestBody Order order) {

        return service.save(order);
    }

    @GetMapping("/zona/{zone}")
    public List<Order> getOrdersByZone(@PathVariable("zone") String zone) {

        return service.getOrdersByZone(zone);
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable("id") Integer id) {
        return service.getOrderById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order order) {

        return service.update(order);
    }

    @GetMapping("/salesman/{id}")
    public List<Order> ordersSalesManByID(@PathVariable("id") Integer id){
        return service.ordersSalesManByID(id);
    }

    @GetMapping("/state/{state}/{id}")
    public List<Order> ordersSalesManByStatus(@PathVariable("state") String state, @PathVariable("id") Integer id){
        return service.ordersSalesManByStatus(id, state);
    }

    @GetMapping("/date/{date}/{id}")
    public List<Order> ordersSalesManByDate(@PathVariable("date") String dateStr, @PathVariable("id") Integer id) throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        return service.ordersSalesManByDate(id, date);
    }
}
