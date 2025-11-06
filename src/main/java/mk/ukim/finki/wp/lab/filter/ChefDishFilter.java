package mk.ukim.finki.wp.lab.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Tuka se stava anotacija WebFilter za da go poznae spring i na koj pateki da slusa t.e da proerura za filtriranje
// od Filter klasata nasleduva i so prave overide na metodot doFilter za da rabote
@WebFilter(filterName = "ChefDishFilter", urlPatterns = {"/dish", "/chefDetails"})
public class ChefDishFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Zemesh gi req i reso=ponsekako HttpServlet verzija da imas funkiccite so ti treba
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Tuka da se gledat koj se stranite na koj treba da ode filtero
        String prvataStrnaSoSiteChefs = "/listChefs";
        String dishRuta = "/dish";
        String chefDetailsRuta = "/chefDetails";

        // Zimame ja patekata na koja e pusten requestot ikoj metod e koristen
        String path = req.getServletPath();
        String method = req.getMethod();

        // Tuka dojde ako ruatata e /dish i proveruva dali go ima id za da najde chef
        // ako nema vraka se na prvata
        if (dishRuta.equals(path)) {
            String chefId = req.getParameter("chefId");
            if (chefId == null || chefId.isBlank()) {
                resp.sendRedirect(prvataStrnaSoSiteChefs);
                return;
            }
        } else if (chefDetailsRuta.equals(path)) {
            // Tuka dojde ako patekata e /chefDetails i prave proverka koj metod e dali get/post
            if ("GET".equalsIgnoreCase(method)) {
                // Tuka provere dali ke odeme da gi prikaze dishovite na shefo dali ima id za nego
                String chefId = req.getParameter("chefId");
                if (chefId == null || chefId.isBlank()) {
                    resp.sendRedirect(prvataStrnaSoSiteChefs);
                    return;
                }
            } else {
                // Tuka dojde koga e post koga za chefo dodademe dish za koj treba da imame id na chefot i dishot
                // vo patekata
                String chefId = req.getParameter("chefId");
                String dishId = req.getParameter("dishId");
                if (chefId == null || chefId.isBlank() || dishId == null || dishId.isBlank()) {
                    resp.sendRedirect(prvataStrnaSoSiteChefs);
                    return;
                }
            }
        }
        // Na kraj ako se e ok samo mine niz filterot i se produzuva kako sto treba da si rabote
        chain.doFilter(request, response);
    }
}
