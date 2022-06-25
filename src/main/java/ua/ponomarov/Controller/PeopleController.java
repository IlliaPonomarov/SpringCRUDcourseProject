package ua.ponomarov.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ponomarov.DAO.PersonDAO;
import ua.ponomarov.Model.Person;
import ua.ponomarov.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonValidator personValidator;
    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person person){

        return "/people/new";
    }

    @PostMapping("/create")
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "/people/new";

        personDAO.save(person);

        return "redirect:/main";
    }

    // Get list of all person
    @GetMapping("/all")
    public String listOfPeople(Model model){

        model.addAttribute("people", personDAO.index());

        return "/people/allPeople";
    }


    @GetMapping("/{id}")
    public String personalPageOfPerson(@PathVariable("id") int id, Model model){

        model.addAttribute("person", personDAO.findById(id));

        return "/people/personalPageOfPerson";
    }

    // Delete Person

    @GetMapping("/delete")
    public String deletePerson(Model model, @ModelAttribute("person") Person person){
        model.addAttribute("people", personDAO.index());

        return "people/deletePerson";
    }


    @DeleteMapping("/delete")
    public String removePerson(@ModelAttribute("person") Person person){

        personDAO.delete(person.getId());

        return "redirect:/people/all";
    }





}
