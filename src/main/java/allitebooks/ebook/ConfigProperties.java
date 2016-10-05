package allitebooks.ebook;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="ebook")
public class ConfigProperties {

	private String location;
	
	private String urlPage;
	
	public String getLocation(){
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrlPage() {
		return urlPage;
	}
	
	public void setUrlPage(String urlPage) {
		this.urlPage = urlPage;
	}
	
}
