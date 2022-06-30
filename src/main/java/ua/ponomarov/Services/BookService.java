package ua.ponomarov.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ponomarov.Model.Book;
import ua.ponomarov.Model.Person;
import ua.ponomarov.Repository.BooksRepository;
import ua.ponomarov.Repository.PeopleRepository;

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
    public Optional<Person> getPerson(int id){

        return peopleRepository.findById(id);
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
        booksRepository.save(book);

    }

}
