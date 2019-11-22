package hibernate.config;

import hibernate.dao.DaoUser;
import hibernate.model.User;
import hibernate.service.Some;
import hibernate.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "hibernate")
public class WebConfig  {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

/*
    @Bean
    public ServiceUser getServiceUser(){
        return new ServiceUser(getDao());
    }
*/

    @Bean
    public UserService getUserService(){
        return new UserService();
    }


/*    @Bean
    public DaoPlanet getDao(){
        return new DaoPlanet();
    }*/

    @Bean
    public DaoUser getDaoUser() { return new DaoUser(); }

    @Bean
    public User user() { return new User(); }

/*    @Bean
    public Planet getPlanet(){
        return new Planet();
    }*/
}
