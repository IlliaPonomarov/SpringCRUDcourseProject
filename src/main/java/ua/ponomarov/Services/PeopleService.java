package ua.ponomarov.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ponomarov.Model.Book;
import ua.ponomarov.Model.Person;
import ua.ponomarov.Repository.PeopleRepository;
import java.util.*;

@Component
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
    public void save(Person person){
        peopleRepository.save(person);
    }

    public void update(int id, Person person){

        person.setId(id);
        peopleRepository.save(person);
    }

    public void delete(int id){

        peopleRepository.deleteById(id);
    }

    public List<Book> allBooks(int id){
        return (Objects.requireNonNull(peopleRepository.findById(id).orElse(null))).getBookList();
    }


}
