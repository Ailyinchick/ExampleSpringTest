package hibernate.model;

import hibernate.config.DataConfig;
import hibernate.config.WebConfig;

import hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;


@ComponentScan(basePackages = "hibernate")
@ContextConfiguration(classes = {WebConfig.class, DataConfig.class})
@PropertySource("classpath:db.properties")
@PropertySource("classpath:hibernate.properties")
@WebAppConfiguration
public class PlanetTest extends AbstractTestNGSpringContextTests {


    @Autowired
    UserService userService;

    @Test
    public void findIdUser() {
        System.out.println(userService.findById("3"));
    }

    @Test
    public void findAll() {
        System.out.println(userService.displayAll());
    }

    @Test
    public void findRichest() {
        System.out.println(userService.showRichest());
    }

    @Test
    public void bankSumm() {
        System.out.println(userService.bankSumm());
    }

}