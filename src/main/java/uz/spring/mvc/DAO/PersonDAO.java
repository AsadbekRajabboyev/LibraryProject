package uz.spring.mvc.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.spring.mvc.model.Book;
import uz.spring.mvc.model.Person;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        List<Person> personList = new ArrayList<>();
        return jdbcTemplate.query("Select * from person",
                new PersonMapper());
    }

    public Person show(int id)   {
        return jdbcTemplate.query("Select * from person where id =?",new Object[]{id},
                        new PersonMapper())
                .stream().findAny().orElse(null);
    }



    public void saveUser(Person person) {
        jdbcTemplate.update("Insert into person(name,age) Values(?,?)",
                person.getName(), person.getAge());
    }

    public void edit(int id, Person yangiPerson) {
        jdbcTemplate.update("Update person set name=?, age=? Where id=?" ,
                yangiPerson.getName(),yangiPerson.getAge(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("Delete from person Where id =?", id);
    }

    public Optional<Person> showAgeError(int age) {
        return jdbcTemplate.query("Select * from person where age =?",
                        new Object[]{age}, new PersonMapper())
                .stream().findAny();
    }
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
