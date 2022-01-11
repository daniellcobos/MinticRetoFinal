package co.edu.mintic.ciclo4.minticciclo4.service;

import co.edu.mintic.ciclo4.minticciclo4.model.Order;
import co.edu.mintic.ciclo4.minticciclo4.model.User;
import co.edu.mintic.ciclo4.minticciclo4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {

        var it = repository.findAll();
        var orders = new ArrayList<Order>();
        it.forEach(order -> orders.add(order));
        return orders;
    }

    public Order save(Order order) {
        if(order.getId() == null) {
            return repository.save(order);
        } else {
            Optional<Order> result = repository.findById(order.getId().toString());
            if(result.isEmpty()) {
                return repository.save(order);
            } else {
                return order;
            }
        }
    }

    public List<Order> getOrdersByZone(String zone) {

        List<Order> orders = repository.getOrdersByZone(zone);
        return orders;
    }

    public Optional<Order> getOrderById(Integer id) {

        Optional<Order> order = repository.getOrderById(id);
        return order;
    }

    public Order update(Order order) {

        if(order.getId() != null && order.getStatus() != null) {

            Optional<Order> result = repository.getOrderById(order.getId());
            if(result.isPresent()) {
                Order existing = result.get();
                existing.setStatus(order.getStatus());
                repository.save(existing);
            }
        }

        return order;
    }

    public List<Order> ordersSalesManByID(Integer id){
        return repository.ordersSalesManByID(id);
    }

    public List<Order> ordersSalesManByStatus(Integer id, String state){
        return repository.ordersSalesManByStatus(id, state);
    }

    public List<Order> ordersSalesManByDate(Integer id, Date dateStr) {
        return repository.ordersSalesManByDate(id, dateStr);
    }
}
