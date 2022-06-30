package ua.ponomarov.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ponomarov.Model.Book;
import ua.ponomarov.Model.Person;
import ua.ponomarov.Repository.PeopleRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Map<Boolean, Book> allBooks(int id){

        Map<Boolean, Book> booleanBookMap = new HashMap<>();

        for (Book book : findById(id).getBookList())
            booleanBookMap.put(date(), book);



        return booleanBookMap;
    }

    public Optional<Person> findByFullName(String fullName){


        return Optional.ofNullable(peopleRepository.findByFullName(fullName));
    }


    public boolean date(){

        Date oldDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        System.out.println("Date from DB: " + oldDate);
        calendar.setTime(oldDate);
        calendar.add(Calendar.DAY_OF_MONTH, 10);

        try {

            Date end = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
            System.out.println("Date from DB: " + simpleDateFormat.format(oldDate));
            System.out.println("End of Date: " + simpleDateFormat.format(end));

            if (oldDate.compareTo(end) == 0 && oldDate.compareTo(end) > 0)
                return true;
            else if (oldDate.compareTo(end) < 0)
                return false;


        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println();


        return false;
    }


}
