package allitebooks.ebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:app-context.xml")
//@EnableAutoConfiguration(exclude={EbookConfiguration.class})
class SpringWebApp {
	
    public static void main(String[] args) {
    //	Configurator.initialize(null, "classpath:log4j.xml");
        SpringApplication.run(SpringWebApp.class, args);
    }
}
