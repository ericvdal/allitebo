package allitebooks.ebook;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import allitebooks.ebook.spring.tasklet.ParseProcessor;
import allitebooks.ebook.spring.tasklet.ParseReader;
import allitebooks.ebook.spring.tasklet.ParseWriter;
import allitebooks.ebooks.spring.model.EbookDetail;

@Configuration
@EnableBatchProcessing
public class EbookConfiguration {
	
	
	@Autowired
	private JobBuilderFactory jobs;
		
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	ParseReader parseReader;
	
	@Autowired
	ParseWriter parseWriter;

	@Autowired
	private ParseProcessor parseProcessor;
	
	@Autowired
	private Tasklet taskletCheck;

	@Autowired
	private JobRepository jobRepository;

	@Bean
	public Job loadAllItEbookJob(){

		return jobs.get("loadAllItEbookJob")
				.incrementer(new RunIdIncrementer())
				.flow(checkIsNext())
				.next(parseStep())
				.end()
				.build();
	}

	@Bean
	Step parseStep() {
		return stepBuilderFactory.get("parse")
				.<EbookDetail, EbookDetail> chunk(1)
				.reader(parseReader)
				.processor(parseProcessor)
				.writer(parseWriter)
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	Step checkIsNext() {
		return stepBuilderFactory.get("check_next")
				.tasklet(taskletCheck)
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(4);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
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
