package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public String register(@RequestParam String workshopTitle,
                           @RequestParam String participantName,
                           @RequestParam String participantEmail,
                           @RequestParam Integer numSeats,
                           @RequestHeader(value = "User-Agent", required = false) String userAgent,
                           HttpServletRequest request,
                           Model model) {
        Registration registration;
        try {
            registration = registrationService.register(
                    workshopTitle, participantName, participantEmail, numSeats);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage() != null ? e.getMessage() : "Invalid registration";
            return "redirect:/workshops?errorMessage="
                    + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }

        model.addAttribute("registration", registration);
        model.addAttribute("workshopTitle", workshopTitle);
        model.addAttribute("participantName", participantName);
        model.addAttribute("participantEmail", participantEmail);
        model.addAttribute("numSeats", numSeats);
        model.addAttribute("ipAddress", request.getRemoteAddr());
        model.addAttribute("userAgent", userAgent);

        return "registrationConfirmation";
    }
}
