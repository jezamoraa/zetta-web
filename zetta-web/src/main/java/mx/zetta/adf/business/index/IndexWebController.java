package mx.zetta.adf.business.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index.html")
public class IndexWebController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sayHello() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mensaje", "pagina Principal Java");
        modelAndView.setViewName(IndexConstants.PATH + "index");
        return modelAndView;
    }
}