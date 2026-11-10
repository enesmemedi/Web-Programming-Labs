package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name="RegistrationServlet", urlPatterns = "/servlet/registration")
public class RegistrationServlet extends HttpServlet {
    private final RegistrationService registrationService;
    private final SpringTemplateEngine springTemplateEngine;

    public RegistrationServlet(RegistrationService registrationService, SpringTemplateEngine springTemplateEngine) {
        this.registrationService = registrationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String workshopTitle = req.getParameter("workshopTitle");
        String participantName = req.getParameter("participantName");
        String participantEmail = req.getParameter("participantEmail");

        Registration registration;
        try {
            int numSeats = Integer.parseInt(req.getParameter("numSeats"));
            registration = registrationService.register(
                    workshopTitle, participantName, participantEmail, numSeats);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage() != null ? e.getMessage() : "Invalid registration";
            resp.sendRedirect(req.getContextPath() + "/servlet/workshops?errorMessage="
                    + URLEncoder.encode(message, StandardCharsets.UTF_8));
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("registration", registration);
        context.setVariable("workshopTitle", workshopTitle);
        context.setVariable("numSeats", req.getParameter("numSeats"));
        context.setVariable("participantName", participantName);
        context.setVariable("participantEmail", participantEmail);
        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("userAgent", req.getHeader("User-Agent"));

        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("registrationConfirmation.html", context, resp.getWriter());

    }
}
