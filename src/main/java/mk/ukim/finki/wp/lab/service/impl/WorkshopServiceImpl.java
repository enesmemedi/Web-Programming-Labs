package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.InstructorRepository;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final InstructorRepository instructorRepository;

    public WorkshopServiceImpl(WorkshopRepository workshopRepository,
                               InstructorRepository instructorRepository) {
        this.workshopRepository = workshopRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Workshop> listAll() {
        return workshopRepository.findAll();
    }

    @Override
    public List<Workshop> search(String text, Double maxPrice) {
        return workshopRepository.search(text, maxPrice);
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return workshopRepository.findById(id);
    }

    @Override
    public Workshop create(String title, String category, Double price, Integer capacity, Long instructorId) {
        Instructor instructor = validate(title, category, price, capacity, instructorId);
        Workshop workshop = new Workshop(title, category, price, capacity, instructor);
        return workshopRepository.save(workshop);
    }

    @Override
    public Workshop update(Long id, String title, String category, Double price, Integer capacity, Long instructorId) {
        Instructor instructor = validate(title, category, price, capacity, instructorId);

        Workshop workshop = workshopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("WorkshopNotFound"));

        workshop.setTitle(title);
        workshop.setCategory(category);
        workshop.setPrice(price);
        workshop.setCapacity(capacity);
        workshop.setInstructor(instructor);

        return workshopRepository.save(workshop);
    }

    @Override
    public void deleteById(Long id) {
        workshopRepository.deleteById(id);
    }

    /**
     * Shared validation for create() and update().
     * Throws IllegalArgumentException with a human readable message,
     * or returns the instructor that was found by instructorId.
     */
    private Instructor validate(String title, String category, Double price, Integer capacity, Long instructorId) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must be a non-negative number");
        }
        if (capacity == null || capacity < 0) {
            throw new IllegalArgumentException("Capacity must be a non-negative number");
        }
        if (instructorId == null) {
            throw new IllegalArgumentException("Instructor is required");
        }
        return instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("InstructorNotFound"));
    }
}
