package ua.ponomarov.DAO;

import org.springframework.stereotype.Component;
import ua.ponomarov.Model.Person;
import java.util.*;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people = new ArrayList<>();

    {
        people.add(new Person(++PEOPLE_COUNT, "Tom", 12, "jkhgjhkg@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 34, "klklk678@pornhub.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 89, "turmalin89@mail.ru"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public void save(Person person){
        person.setId(PEOPLE_COUNT++);
        people.add(person);
    }

    public void update(int id, Person person){
        Person person1 = findById(id);

        person1.setName(person.getName());
        person1.setAge(person.getAge());
        person1.setEmail(person.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }

    public Person findById(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

}
