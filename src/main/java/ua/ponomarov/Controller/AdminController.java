package ua.ponomarov.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.ponomarov.DAO.PersonDAO;
import ua.ponomarov.Model.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping
    public String adminPage(@ModelAttribute("person") Person person, Model model){
        model.addAttribute("people", personDAO.getPeople());

        return "people/adminPage";

    }

    @PatchMapping("/add")
    public String adminAdd(@ModelAttribute("person") Person person){
        System.out.println(person.getId());

        return "redirect:/people";
    }
}
