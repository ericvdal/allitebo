package allitebooks.ebook;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
//@Import({DatabaseAccessConfiguration.class, ServicesConfiguration.class})
public class EbookConfiguration {
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	

	@Bean
	public Job loadAllItEbookJob(){
		return jobs.get("loadAllItEbookJob")
					.start(step())
					.build();
	}

/*
	private Step step() {
		//return stepBuilderFactory.get("parse");
	}
	*/
/*
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	  YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	  yaml.setResources(new ClassPathResource("default.yml"));
	  propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
	  return propertySourcesPlaceholderConfigurer;
	}
	*/
}
