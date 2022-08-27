package control;

import model.CommandService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/*")
public class FrontController extends HttpServlet {

    private TemplateEngine engine;
    private CommandService commandService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        engine = new TemplateEngine();
        super.init(config);

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix(config.getServletContext().getRealPath("") + "\\");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);

        try {
            commandService = new CommandService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            commandService.process(req, resp, engine);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
