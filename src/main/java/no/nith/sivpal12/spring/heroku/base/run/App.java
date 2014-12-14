package no.nith.sivpal12.spring.heroku.base.run;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class App {
    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_PACKAGE_LOCATION = "no.nith.sivpal12.spring.heroku.base.spring.config";
    private static final String MAPPING_URL = "/";

    private App(){
    }

    public static void main(String[] args) throws Exception {
        int port;
        try {
            port = Integer.valueOf(System.getenv("PORT"));
        } catch (NumberFormatException e) {
            port = 8080;
        }

        Server server = new Server(port);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
        server.join();
    }

    private static WebAppContext getServletContextHandler(WebApplicationContext context) throws IOException {
        WebAppContext contextHandler = new WebAppContext();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_PACKAGE_LOCATION);
        return context;
    }

}
