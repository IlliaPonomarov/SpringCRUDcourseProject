package ua.ponomarov.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ponomarov.Model.Book;
import ua.ponomarov.Model.Person;
import ua.ponomarov.Repository.BooksRepository;
import ua.ponomarov.Repository.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;


    @Autowired
    public BookService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    // http://localhost:7778/books?page=1&books_per_page=6&sort_by_year=true


    public List<Book> findAll(int page, int booksPerPage, boolean sort_by_year){
        Pageable paging = null;

        if (sort_by_year)
            paging = PageRequest.of(page, booksPerPage, Sort.by("year"));
        else
            paging = PageRequest.of(page, booksPerPage);

        Page<Book>  pageResult = booksRepository.findAll(paging);

        if (pageResult.hasContent())
            return pageResult.getContent();

        return null;
    }



    public Book findById(int id){

        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }


    @Transactional
    public void update(int id, Book book){

        book.setBook_id(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id){

        booksRepository.deleteById(id);
    }

    @Transactional
    public Optional<Person> getPerson(Integer id){

        if (id == null)
            return Optional.empty();

        Book book = findById(id);

        Optional<Person> person = Optional.ofNullable(book.getPerson());

        return person;
    }

    @Transactional
    public void free(int id){
        Book book = findById(id);
        book.setPerson(null);

        book.setBook_id(id);

        booksRepository.save(book);

    }

    @Transactional
    public void setNewOwner(Person person, int id){

        Book book = findById(id);
        book.setPerson(person);
        book.setDateTakeOfBook(new Date());

        booksRepository.save(book);

    }

    public Optional<Book> findByNameContaining(String title){
        return Optional.ofNullable(booksRepository.findByNameContaining(title));
    }




}
