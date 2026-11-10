package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopRepository {
    List<Workshop> findAll();
    Optional<Workshop> findByTitle(String title);
    List<Workshop> search(String text, Double maxPrice);
    Optional<Workshop> findById(Long id);
    Workshop save(Workshop workshop);
    void deleteById(Long id);
}
