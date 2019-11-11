package hibernate.model;


import hibernate.config.DataConfig;
import hibernate.config.WebConfig;
import hibernate.controller.DeveloperController;
import hibernate.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ComponentScan(basePackages = "hibernate")
@ContextConfiguration(classes = {WebConfig.class, DataConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class ServiceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DeveloperController developerController;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(developerController).build();
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }


    @Test
    public void second() throws Exception {
        when(userService.displayAll()).thenReturn("Hello from mockito");

        mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("allUsers", "Hello from mockito"));

    }


    @Test
    public void findbyid() throws Exception {
        for (int i = 1; i < 9; i++) {
            this.mockMvc.perform(post("/findById").param("id", "" + i)
                    .accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andDo(print()).
                    andExpect(view().name("index")).andExpect(model().
                    attribute("userById", containsString(userService.findById("" + i))));
        }
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/findAll").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).
                andExpect(view().name("index")).andExpect(model().
                attribute("allUsers", containsString(userService.displayAll()))
        );
    }

    @Test
    public void bankSum() throws Exception {
        assertThat(
                mockMvc.perform(get("/bankSumm").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).
                        andExpect(view().name("index")).andExpect(model().
                        attribute("bankSumm", containsString(userService.bankSumm())))
        );

    }

    @Test
    public void findRichest() throws Exception {
        assertThat(
                mockMvc.perform(get("/findRichest").accept(MediaType.TEXT_HTML))
                        .andExpect(status().isOk()).andExpect(view().name("index"))
                        .andExpect(model().attribute("richestUser", containsString(userService.showRichest())))
        );
    }
}
