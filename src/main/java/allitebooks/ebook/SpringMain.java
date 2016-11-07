package allitebooks.ebook;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;

public class SpringMain {

	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		
	//	EbookService ebookService = context.getBean("ebookService", EbookService.class); 
		
		Job loadAllItEbookJob  = context.getBean("loadAllItEbookJob", Job.class); 
		
		//List<EbookDetail> ebookDetailList = ebookService.getAll();
		
		JobRepository jobRepository  = context.getBean("jobRepository", JobRepository.class) ; 
		
		
		TaskExecutor taskExecutor  =  context.getBean("taskExecutor", TaskExecutor.class); 
		
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(taskExecutor);
		
		
		JobParameters jobParameters = new JobParameters();
		try {
			jobLauncher.run(loadAllItEbookJob, jobParameters );
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		context.getBeanDefinitionCount();
	}
	
}
