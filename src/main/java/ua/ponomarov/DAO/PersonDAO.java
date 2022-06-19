package ua.ponomarov.DAO;

import org.springframework.stereotype.Component;
import ua.ponomarov.Model.Person;

import java.sql.*;
import java.util.*;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/spring_test";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM people";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

    public void save(Person person){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO people VALUES(1, ?, ?, ?)");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(int id, Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE people SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM people WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person findById(int id){
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM people WHERE id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return person;
    }

}
