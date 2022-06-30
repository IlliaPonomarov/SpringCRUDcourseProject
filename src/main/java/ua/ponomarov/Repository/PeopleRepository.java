package ua.ponomarov.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ponomarov.Model.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
