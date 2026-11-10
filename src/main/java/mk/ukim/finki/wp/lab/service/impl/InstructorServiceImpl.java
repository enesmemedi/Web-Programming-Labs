package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.repository.InstructorRepository;
import mk.ukim.finki.wp.lab.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    InstructorRepository instructorRepository;
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }
    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }
}
