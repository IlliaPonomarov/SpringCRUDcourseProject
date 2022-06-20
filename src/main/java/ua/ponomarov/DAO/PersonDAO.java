package ua.ponomarov.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.ponomarov.Model.Person;

import java.sql.*;
import java.util.*;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {

        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO people VALUES (1, ?, ?, ?)",
                person.getName(),
                person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person person){

        jdbcTemplate.update("UPDATE people SET name=?, age=?, email=? WHERE id=?", person.getName(), person.getAge(), person.getEmail(), id);

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
    }

    public Person findById(int id){

        return jdbcTemplate.query("SELECT * FROM people WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    ///////////////////
    // TBatch Test
    //////////////////

    public void testMultiplyUpdate(){
        List<Person> people = create1000People();

        long before = System.currentTimeMillis();


        for (Person p : people) {
            jdbcTemplate.update("INSERT INTO people VALUES(?, ?, ?, ?)", p.getId(), p.getName(), p.getAge(), p.getEmail());
        }

        long after = System.currentTimeMillis();

        System.out.println("Time " + (after - before));
    }

    public void testBatchUpdate(){

        List<Person> people = create1000People();

        long before = System.currentTimeMillis();

            jdbcTemplate.batchUpdate("INSERT INTO people VALUES(?, ?, ?, ?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, people.get(i).getId());
                    ps.setString(2, people.get(i).getName());
                    ps.setInt(3, people.get(i).getId());
                    ps.setString(4, people.get(i).getEmail());
                }

                @Override
                public int getBatchSize() {
                    return people.size();
                }
            });

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (after - before));

    }

    public List<Person> create1000People(){

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name", 30 , "test@gmail.com"));
        }
        return people;
    }



}
