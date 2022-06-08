package ua.ponomarov.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {



    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "secondname", required = false) String surname,
                        Model model){

        model.addAttribute("text", "Hello " + name + "\t" + surname);
        return "hello/hello_world";
    }

    @GetMapping("/goodbye")
    public String goodbye(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "secondname", required = false) String surname,
                          Model model) {
        model.addAttribute("text", "Hello " + name + "\t" + surname);
        return "hello/goodbye";
    }
}
