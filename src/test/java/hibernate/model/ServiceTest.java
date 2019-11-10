package hibernate.model;


import hibernate.controller.DeveloperController;
import hibernate.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DeveloperController.class, UserService.class})
@AutoConfigureMockMvc
public class ServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService ms;

    @Test
    public void findbyid() throws Exception {
        for (int i = 1; i < 9; i++) {
            mockMvc.perform(post("/findById").param("id", "" + i)
                    .accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andDo(print()).
                    andExpect(view().name("index")).andExpect(model().
                    attribute("userById", containsString(ms.findById("" + i))));
        }
    }

    @Test
    public void findAll() throws Exception {
        assertThat(
                mockMvc.perform(get("/findAll").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).
                        andExpect(view().name("index")).andExpect(model().
                        attribute("allUsers", containsString(ms.displayAll()))
                )
        );
    }

    @Test
    public void bankSum() throws Exception {
        assertThat(
                mockMvc.perform(get("/bankSumm").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).
                        andExpect(view().name("index")).andExpect(model().
                        attribute("bankSumm", containsString(ms.bankSumm())))
        );

    }

    @Test
    public void findRichest() throws Exception {
        assertThat(
                mockMvc.perform(get("/findRichest").accept(MediaType.TEXT_HTML))
                        .andExpect(status().isOk()).andExpect(view().name("index"))
                        .andExpect(model().attribute("richestUser", containsString(ms.showRichest())))
        );
    }
}
