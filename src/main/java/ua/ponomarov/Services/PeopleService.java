package ua.ponomarov.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ponomarov.Model.Book;
import ua.ponomarov.Model.Person;
import ua.ponomarov.Repository.PeopleRepository;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;


    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){

        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person){

        person.setId(id);
        peopleRepository.save(person);
    }


    @Transactional
    public void delete(int id){

        peopleRepository.deleteById(id);
    }

    public List<Book> allBooks(int id){

        for (Book book : findById(id).getBookList())
            System.out.println(book.getName());

        return (Objects.requireNonNull(peopleRepository.findById(id).orElse(null))).getBookList();
    }

    public Optional<Person> findByFullName(String fullName){


        return Optional.ofNullable(peopleRepository.findByFullName(fullName));
    }


}
