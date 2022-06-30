package ua.ponomarov.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ponomarov.Model.Person;
import ua.ponomarov.Repository.PeopleRepository;
import ua.ponomarov.Services.PeopleService;
import ua.ponomarov.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonValidator personValidator;
    private PeopleService peopleService;

    @Autowired
    public PeopleController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
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

        peopleService.save(person);

        return "redirect:/people";
    }

    // Get list of all person
    @GetMapping
    public String listOfPeople(Model model){

        model.addAttribute("people", peopleService.findAll());

        return "/people/allPeople";
    }


    @GetMapping("/{id}")
    public String personalPageOfPerson(@PathVariable("id") int id, Model model){

        model.addAttribute("person", peopleService.findById(id));
        model.addAttribute("books", peopleService.allBooks(id));

        return "/people/personalPageOfPerson";
    }




    @DeleteMapping("/{id}")
    public String removePerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){

        peopleService.delete(person.getId());

        return "redirect:/people/";
    }



    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }




}
