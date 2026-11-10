package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.InstructorService;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Controller
public class WorkshopController {

    private final WorkshopService workshopService;
    private final InstructorService instructorService;

    public WorkshopController(WorkshopService workshopService, InstructorService instructorService) {
        this.workshopService = workshopService;
        this.instructorService = instructorService;
    }

    /* ---------- 4.1  listing + search ---------- */

    @GetMapping("/")
    public String redirectToWorkshops() {
        return "redirect:/workshops";
    }

    @GetMapping("/workshops")
    public String getWorkshopsPage(@RequestParam(required = false) String text,
                                   @RequestParam(required = false) Double maxPrice,
                                   @RequestParam(required = false) String errorMessage,
                                   Model model) {
        // an empty text box arrives as "" - treat it as "no filter"
        if (text != null && text.isBlank()) {
            text = null;
        }

        List<Workshop> workshops;
        if (text != null || maxPrice != null) {
            workshops = workshopService.search(text, maxPrice);
        } else {
            workshops = workshopService.listAll();
        }

        model.addAttribute("workshops", workshops);
        if (errorMessage != null && !errorMessage.isBlank()) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "listWorkshops";
    }

    /* ---------- 7.  the two form pages ---------- */

    @GetMapping("/workshops/workshop-form")
    public String getAddWorkshopPage(Model model) {
        model.addAttribute("instructors", instructorService.findAll());
        return "workshop-form";
    }

    @GetMapping("/workshops/workshop-form/{id}")
    public String getEditWorkshopForm(@PathVariable Long id, Model model) {
        Optional<Workshop> workshop = workshopService.findById(id);
        if (workshop.isEmpty()) {
            return "redirect:/workshops?errorMessage=WorkshopNotFound";
        }
        model.addAttribute("workshop", workshop.get());
        model.addAttribute("instructors", instructorService.findAll());
        return "workshop-form";
    }

    /* ---------- 4.2  add ---------- */

    @PostMapping("/workshops/add")
    public String saveWorkshop(@RequestParam String title,
                               @RequestParam String category,
                               @RequestParam Double price,
                               @RequestParam Integer capacity,
                               @RequestParam Long instructorId) {
        try {
            workshopService.create(title, category, price, capacity, instructorId);
        } catch (IllegalArgumentException e) {
            return redirectWithError(e);
        }
        return "redirect:/workshops";
    }

    /* ---------- 4.3  edit ---------- */

    @PostMapping("/workshops/edit/{workshopId}")
    public String editWorkshop(@PathVariable Long workshopId,
                               @RequestParam String title,
                               @RequestParam String category,
                               @RequestParam Double price,
                               @RequestParam Integer capacity,
                               @RequestParam Long instructorId) {
        try {
            workshopService.update(workshopId, title, category, price, capacity, instructorId);
        } catch (IllegalArgumentException e) {
            return redirectWithError(e);
        }
        return "redirect:/workshops";
    }

    /* ---------- 4.4  delete ---------- */

    @PostMapping("/workshops/delete/{id}")
    public String deleteWorkshop(@PathVariable Long id) {
        workshopService.deleteById(id);
        return "redirect:/workshops";
    }

    /* ---------- helper ---------- */

    private String redirectWithError(IllegalArgumentException e) {
        String message = e.getMessage() != null ? e.getMessage() : "Invalid workshop data";
        return "redirect:/workshops?errorMessage="
                + URLEncoder.encode(message, StandardCharsets.UTF_8);
    }
}
