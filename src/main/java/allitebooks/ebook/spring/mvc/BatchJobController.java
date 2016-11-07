package allitebooks.ebook.spring.mvc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import allitebooks.ebook.spring.service.EbookService;
import allitebooks.ebooks.spring.model.EbookDetail;

@RestController
public class BatchJobController {
	
	@Autowired
	EbookService ebookService;
	
/*
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	*/
	@GetMapping("/batch/job")
	public Boolean job(){
		/*
		Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
		parameters.put("date", new JobParameter(new Date()));
		
			try {
				jobLauncher.run(job, new JobParameters(parameters));

				return Boolean.TRUE;
				
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();

				return Boolean.FALSE;
			}

		*/	
	
		return true;
	}

	@GetMapping("/job/all")
	public @ResponseBody List<EbookDetail> getAll() {
		
		return ebookService.getAll();
		
	}
	
}
