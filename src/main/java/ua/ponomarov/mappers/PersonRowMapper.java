package ua.ponomarov.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.ponomarov.Model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("person_id"));
        person.setFullName(rs.getString("full_name"));
        person.setYearOfBirth(rs.getString("year_of_birth"));

        return person;
    }
}
