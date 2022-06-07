package ua.ponomarov.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/first")
public class HelloController {



    @GetMapping("/hello")
    public String hello(){
        return "hello/hello_world";
    }

    @GetMapping("/goodbye")
    public String goodbye(){
        return "hello/goodbye";
    }
}
