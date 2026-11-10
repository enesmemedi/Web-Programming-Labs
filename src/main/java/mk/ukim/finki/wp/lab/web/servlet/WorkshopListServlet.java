package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="WorkshopListServlet", urlPatterns = "/servlet/workshops")
@AllArgsConstructor
public class WorkshopListServlet extends HttpServlet {
    private final WorkshopService workshopService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        String text = req.getParameter("text");
        String maxPriceStr = req.getParameter("maxPrice");
        if (text != null && text.isBlank()) text = null;
        Double maxPrice = (maxPriceStr == null || maxPriceStr.isBlank()) ? null : Double.parseDouble(maxPriceStr);

        List<Workshop> workshops = workshopService.listAll();
        if (text != null || maxPrice != null) {
                workshops = workshopService.search(text, maxPrice); }
        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", req.getParameter("errorMessage"));

        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("listWorkshops.html", context, resp.getWriter());
    }
}
