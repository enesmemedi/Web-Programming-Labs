package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryWorkshopRepository implements WorkshopRepository {

    @Override
    public List<Workshop> findAll() {
        return DataHolder.workshops;
    }

    @Override
    public Optional<Workshop> findByTitle(String title) {
        return DataHolder.workshops.stream()
                .filter(w -> w.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public List<Workshop> search(String text, Double maxPrice) {
        return DataHolder.workshops.stream()
                .filter(w -> maxPrice == null || w.getPrice() <= maxPrice)
                .filter(w -> text == null || text.isEmpty()
                        || w.getTitle().toLowerCase().contains(text.toLowerCase())
                        || w.getCategory().toLowerCase().contains(text.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return DataHolder.workshops.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst();
    }

    @Override
    public Workshop save(Workshop workshop) {
        Optional<Workshop> existing = findById(workshop.getId());
        if (existing.isPresent()) {
            // replace in place, so the workshop keeps its position in the list
            int index = DataHolder.workshops.indexOf(existing.get());
            DataHolder.workshops.set(index, workshop);
        } else {
            DataHolder.workshops.add(workshop);
        }
        return workshop;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.workshops.removeIf(w -> w.getId().equals(id));
    }
}
