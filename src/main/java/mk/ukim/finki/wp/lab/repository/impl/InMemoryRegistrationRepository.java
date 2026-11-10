package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.repository.RegistrationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryRegistrationRepository implements RegistrationRepository {

    @Override
    public Registration save(Registration registration) {
        DataHolder.registrations.add(registration);
        return registration;
    }

    @Override
    public List<Registration> findAll() {
        return DataHolder.registrations;
    }
}
