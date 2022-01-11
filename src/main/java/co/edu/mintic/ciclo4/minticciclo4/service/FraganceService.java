package co.edu.mintic.ciclo4.minticciclo4.service;

import co.edu.mintic.ciclo4.minticciclo4.model.Fragance;
import co.edu.mintic.ciclo4.minticciclo4.model.Order;
import co.edu.mintic.ciclo4.minticciclo4.repository.FraganceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class FraganceService {


    @Autowired
    private FraganceRepository repository;

    public List<Fragance> findAll() {

        var it = repository.findAll();
        var fragances = new ArrayList<Fragance>();
        it.forEach(fragance -> fragances.add(fragance));
        return fragances;
    }

    public Fragance save(Fragance fragance) {
        if(fragance.getReference() == null) {
            return repository.save(fragance);
        } else {
            Optional<Fragance> result = repository.findById(fragance.getReference());
            if(result.isEmpty()) {
                return repository.save(fragance);
            } else {
                return fragance;
            }
        }
    }

    public Fragance update(Fragance fragance) {

        if(fragance.getReference() == null) {
            return repository.save(fragance);
        } else {
            Optional<Fragance> result = repository.findById(fragance.getReference());
            if(result.isPresent()) {

                Fragance existing = result.get();
                existing.setBrand(fragance.getBrand());
                existing.setCategory(fragance.getCategory());
                existing.setPresentation(fragance.getPresentation());
                existing.setDescription(fragance.getDescription());
                existing.setAvailability(fragance.isAvailability());
                existing.setPrice(fragance.getPrice());
                existing.setQuantity(fragance.getQuantity());
                existing.setPhotography(fragance.getPhotography());

                return repository.save(existing);
            } else {
                return fragance;
            }
        }
    }

    public boolean delete(String reference) {

        repository.deleteById(reference);
        return true;
    }

    public Optional<Fragance> getFraganceByReference(String reference) {

        Optional<Fragance> fragance = repository.getFraganceByReference(reference);
        return fragance;
    }

    public List<Fragance> gadgetsByPrice(double price) {
        return repository.gadgetsByPrice(price);
    }

    public List<Fragance> findByDescriptionLike(String description) {
        return repository.findByDescriptionLike(description.toUpperCase(Locale.ROOT));
    }
}
