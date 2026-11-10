package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.repository.InstructorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryInstructorRepository implements InstructorRepository {

    @Override
    public List<Instructor> findAll() {
        return DataHolder.instructors;
    }

    @Override
    public Optional<Instructor> findById(Long id) {
        return DataHolder.instructors.stream().filter(i -> i.getId().equals(id))
                .findFirst();
    }
}
