package co.edu.mintic.ciclo4.minticciclo4.repository;

import co.edu.mintic.ciclo4.minticciclo4.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{'salesMan.zone' : { $eq: ?0 }}")
    List<Order> getOrdersByZone(String zone);

    @Query("{id : { $eq: ?0 }}")
    Optional<Order> getOrderById(Integer id);

    @Query("{'salesMan.id' : { $eq: ?0 }}")
    List<Order> ordersSalesManByID(Integer id);

    @Query("{'salesMan.id' : { $eq: ?0 }, 'status' : { $eq: ?1 }}")
    List<Order> ordersSalesManByStatus(Integer id, String status);

    @Query("{'salesMan.id' : { $eq: ?0 }, 'registerDay' : {'$gte': ?1}}")
    List<Order> ordersSalesManByDate(Integer id, Date dateStr);
}
