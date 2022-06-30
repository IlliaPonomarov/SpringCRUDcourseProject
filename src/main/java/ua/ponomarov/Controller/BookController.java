package ua.ponomarov.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ponomarov.Model.Book;
import ua.ponomarov.Model.Person;
import ua.ponomarov.Services.BookService;
import ua.ponomarov.Services.PeopleService;
import java.util.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public BookController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(
                        Model model,
                        @RequestParam("page") int page,
                        @RequestParam("books_per_page") int booksPerPage,
                        @RequestParam("sort_by_year") boolean sortByYear) {

        List<Book> books = bookService.findAll(page, booksPerPage, sortByYear);

        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){

        model.addAttribute("book", bookService.findById(id));

        Optional<Person> bookOwner = bookService.getPerson(id);


        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", peopleService.findAll());

        return "books/bookPage";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {

        return "books/new";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "/books/new";

         bookService.save(book);

        return "redirect:/books";
    }


    @PatchMapping("/{id}/edit-person-id")
    public String updatePersonId(@PathVariable("id") int id, @ModelAttribute Person person){

        // Установить нового владельца книги
        bookService.setNewOwner(person, id);

        return "redirect:/books";
    }


    @PatchMapping("/{id}/free")
    public String free(@PathVariable("id") int id){

        bookService.free(id);


        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model){

        model.addAttribute("book", bookService.findById(id));

        return "books/edit";
    }


    @PatchMapping("/{id}/edit")
    public String editForm(@PathVariable("id") int id,
                           @ModelAttribute("book")  @Valid Book book
    , BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "/books/edit";

        bookService.update(id, book);


        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){

        bookService.delete(id);

        return "redirect:/books";
    }



}