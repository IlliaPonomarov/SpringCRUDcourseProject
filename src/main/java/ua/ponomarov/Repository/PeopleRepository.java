package ua.ponomarov.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ponomarov.Model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Person findByFullName(String name);
}
