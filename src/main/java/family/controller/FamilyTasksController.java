package family.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


//import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import family.dao.*;
import family.model.Person;


@Controller
public class FamilyTasksController {
	
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "family";
    }

    /*@RequestMapping("/list")
    public void person(@RequestParam(value="name", defaultValue="World") String name) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        
        PersonDAO personDAO = context.getBean(PersonDAO.class);
         
        List<Person> list = personDAO.list();
         
        for(Person p : list){
            System.out.println("Person List::"+p);
        }
        //close resources
        context.close();   
    }

    @RequestMapping("/test")
    public Person personTest(@RequestParam(value="name", defaultValue="World") String name) {
        Person person1 = new Person();
        person1.setName(name);
        return person1;
    }*/
        
    
}
