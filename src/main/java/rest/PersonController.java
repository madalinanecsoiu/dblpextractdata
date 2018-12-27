package rest;


import model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;


@Controller
@RequestMapping("/person")
public interface PersonController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<Person> getAllPersonsByName(@RequestBody Person personInfo) ;


}
