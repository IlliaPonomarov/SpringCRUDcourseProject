package ua.ponomarov.Controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ponomarov.DAO.PersonDAO;
import ua.ponomarov.Model.Person;
import ua.ponomarov.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    private PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }


    @GetMapping
    public String index(Model model){

        model.addAttribute("people", personDAO.getPeople());
        return "people/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.findById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResultPerson){

        personValidator.validate(person, bindingResultPerson);

        if (bindingResultPerson.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {


        model.addAttribute("person", personDAO.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

    

}
