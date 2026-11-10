package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Registration;

public interface RegistrationService {
    Registration register(String workshopTitle, String participantName, String participantEmail, int numberOfSeats);
}
