package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository {
    List<Instructor> findAll();
    Optional<Instructor> findById(Long id);
}
