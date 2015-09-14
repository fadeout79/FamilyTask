package family.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import family.model.Person;
import family.service.PersonService;

import java.sql.Date;
import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class FamilyTasksControllerTest {
 
    private MockMvc mockMvc;
 
    @Autowired
    private PersonService personServiceMock;
 
    //Add WebApplicationContext field here
 
    //The setUp() method is omitted.
 
    @Test
    public void findAll_ShouldAddPersonEntriesToModelAndRenderPersonListView() throws Exception {
    	Person first = new Person();
        first.setId(1);
		first.setName("Ian");
		first.setParent(true);
		first.setDateOfBirth(new Date(1979,7,30));
 
        Person second = new Person();
		second.setId(2);
        second.setName("Marilune");
        second.setParent(false);
        second.setDateOfBirth(new Date(2007,12,20));

        when(personServiceMock.listPersons()).thenReturn(Arrays.asList(first, second));
 
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(view().name("todo/list"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/todo/persons.jsp"))
                .andExpect(model().attribute("todos", hasSize(2)))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("description", is("Lorem ipsum")),
                                hasProperty("title", is("Foo"))
                        )
                )))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("description", is("Lorem ipsum")),
                                hasProperty("title", is("Bar"))
                        )
                )));
 
        verify(personServiceMock, times(1)).listPersons();
        verifyNoMoreInteractions(personServiceMock);
    }
}



