package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;

import java.util.List;

public interface RegistrationRepository {
    Registration save(Registration registration);
    List<Registration> findAll();
}
