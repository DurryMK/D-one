package spm.app.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spm.app.Filter.AccessFilter;

@Configuration
public class FilterConfig {


//    @Bean
//    public FilterRegistrationBean registFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
////        registration.setFilter(new AccessFilter());
////        registration.addUrlPatterns("/*");
////        registration.setName("AccessFilter");
////        registration.setOrder(1);
//        return registration;
//    }

}
