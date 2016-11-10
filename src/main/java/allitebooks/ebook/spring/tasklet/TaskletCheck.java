package allitebooks.ebook.spring.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import allitebooks.ebook.spring.service.ParserService;

@StepScope
@Component
public class TaskletCheck implements Tasklet{
	

	Logger logger = LoggerFactory.getLogger(TaskletCheck.class);
	
	private ParserService parseService; 
	
	public TaskletCheck(ParserService parseService){
		this.parseService = parseService;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		int totalPage = parseService.getTotalPage();
	//	Integer currentPage = (Integer) chunkContext.getAttribute("currentPage");
		
		Integer currentPage = (Integer) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("currentPage");
		
		if (currentPage == null)
			currentPage  = 0;
	//		currentPage  = 574;
		else 
			currentPage ++;
		if (totalPage >= currentPage) {
			contribution.setExitStatus(ExitStatus.COMPLETED);
		} else {
			contribution.setExitStatus(ExitStatus.STOPPED);
		}
		logger.debug("currentPage: {}", currentPage);
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putInt("currentPage", currentPage);
	//	chunkContext.setAttribute("currentPage", currentPage);
		return null;
	}


}
