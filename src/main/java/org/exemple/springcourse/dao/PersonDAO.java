package org.exemple.springcourse.dao;

import org.exemple.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.rmi.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   // private static int PEOPLE_COUNT;
//    private List<Person> people;
//
//    {
//        people = new ArrayList<>();
//
//        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@mail.ru"));
//        people.add(new Person(++PEOPLE_COUNT, "Bob", 52, "bob@mail.ru"));
//        people.add(new Person(++PEOPLE_COUNT, "Mike", 18, "mike@yahoo.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Katy", 34, "katy@gmail.com"));
//    }
//    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
//    private static final String USERNAME ="postgres";
//    private static final String PASSWORD ="1";
//    private static Connection connection;
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public List<Person> index() {
                //return people;
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//            while (resultSet.next()){
//                Person person = new Person();
//               person.setId(resultSet.getInt("id"));
//               person.setName(resultSet.getString("name"));
//                person.setEmail(resultSet.getString("email"));
//                person.setAge(resultSet.getInt("age"));
//                people.add(person);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return people;
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class) );
    }

    public Person show(int id)
    {
       // return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "ISERT INTO Person VALUES";
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail());

    }
    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }
public void delete(int id){
        //people.removeIf(p-> p.getId()==id);
    jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

}
}