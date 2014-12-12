package no.nith.sivpal12.spring.heroku.base.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @RequestMapping()
    public ModelAndView hello() {
        return new ModelAndView("helloWorld");
    }

}
