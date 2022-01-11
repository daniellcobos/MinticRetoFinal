package co.edu.mintic.ciclo4.minticciclo4.repository;

import co.edu.mintic.ciclo4.minticciclo4.model.Fragance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FraganceRepository extends MongoRepository<Fragance, String> {

    @Query("{reference : { $eq: ?0 }}")
    Optional<Fragance> getFraganceByReference(String reference);

    @Query("{price : { $eq: ?0 }}")
    List<Fragance> gadgetsByPrice(double price);

    @Query("{description : { $regex : ?0 }}")
    List<Fragance> findByDescriptionLike(String description);
}
