package ua.ponomarov.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.ponomarov.Model.Person;
import ua.ponomarov.mappers.PersonRowMapper;

import java.util.*;


@Component
public class PersonDAO {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    public Person findById(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?" , new Object[]{id}, new PersonRowMapper()).stream().findAny().orElse(null);
    }

    public Optional<Person> findByFullName(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",
                new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }


    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(full_name, year_of_birth) VALUES (?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(Person person, int id){
        jdbcTemplate.update("UPDATE person SET full_name=?, year_of_bith=? WHERE person_id=?", person.getFullName(), person.getYearOfBirth(), id);
    }





}
