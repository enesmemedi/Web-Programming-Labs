package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;
    public static List<Instructor> instructors = null;

    @PostConstruct
    public void init() {
        instructors = new ArrayList<>();
        instructors.add(new Instructor("Adam", "Surovski", "adam.sur22@gmail.com", "Software Engineering"));
        instructors.add(new Instructor("Ana", "Bananova", "ana.banana@gmail.com", "Data Science"));
        instructors.add(new Instructor("Steve", "Peevski", "steve.peeve@gmail.com", "Cooking"));

        workshops = new ArrayList<>();
        workshops.add(new Workshop("Intro to Java", "Programming", 1500.0, 20, instructors.get(0)));
        workshops.add(new Workshop("Spring Boot Basics", "Programming", 2500.0, 15, instructors.get(0)));
        workshops.add(new Workshop("Web Design with HTML & CSS", "Design", 1200.0, 25, instructors.get(0)));
        workshops.add(new Workshop("UI/UX Fundamentals", "Design", 1800.0, 10, instructors.get(0)));
        workshops.add(new Workshop("Digital Photography", "Photography", 2000.0, 12, instructors.get(2)));
        workshops.add(new Workshop("Public Speaking", "Soft Skills", 900.0, 30, instructors.get(1)));
        workshops.add(new Workshop("Data Analysis with Python", "Data Science", 3000.0, 8, instructors.get(1)));
        workshops.add(new Workshop("Machine Learning Intro", "Data Science", 3500.0, 0, instructors.get(1)));
        workshops.add(new Workshop("Creative Writing", "Literature", 700.0, 18, instructors.get(1)));
        workshops.add(new Workshop("Cooking Masterclass", "Culinary", 2200.0, 6, instructors.get(2)));

        registrations = new ArrayList<>();
    }
}
