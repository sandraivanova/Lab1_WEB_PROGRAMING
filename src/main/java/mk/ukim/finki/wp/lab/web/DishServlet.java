package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.impl.ChefService;
import mk.ukim.finki.wp.lab.service.impl.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final DishService dishService;
    private final ChefService chefService;
    private final SpringTemplateEngine templateEngine;


    public DishServlet(DishService dishService, ChefService chefService, SpringTemplateEngine templateEngine) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        Long chefId = null;
        try {
            String param = req.getParameter("chefId");
            if (param != null && !param.isEmpty()) {
                chefId = Long.valueOf(param);
            }
        } catch (NumberFormatException ignored) {}

        Chef chef = chefService.findById(chefId);
        WebContext context=new WebContext(webExchange);
        context.setVariable("chefId", chefId);
        context.setVariable("dishes", dishService.listDishes());
        context.setVariable("chefName", chef.getFirstName()+ " "+chef.getLastName());

        templateEngine.process("dishesList.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId = req.getParameter("chefId");
        resp.sendRedirect("/dish?chefId=" + chefId);    }
}
