package allitebooks.ebook.spring.hibernate.utils;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbUtils {

	@Autowired
	 private DataSource dataSource;
	
}
