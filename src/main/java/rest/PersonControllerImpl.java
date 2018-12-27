package rest;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import service.PersonService;

import java.util.ArrayList;
import java.util.List;

public class PersonControllerImpl implements PersonController {

    @Autowired
    PersonService personService;

    public List<Person> getAllPersonsByName(Person personInfo) {
        //create a request to dblp person service
        //parse response and return all names and keys
        List<Person> listOfPersons = new ArrayList<Person>();

        return null;
    }
}
