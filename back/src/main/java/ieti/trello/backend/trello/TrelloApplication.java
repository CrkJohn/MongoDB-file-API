package ieti.trello.backend.trello;

import com.mongodb.client.gridfs.model.GridFSFile;
import ieti.trello.backend.trello.config.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import java.net.*;

@SpringBootApplication
public class TrelloApplication implements CommandLineRunner {



	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/v1/user/*");
		registrationBean.addUrlPatterns("/v1/task/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(TrelloApplication.class, args);
	}

	@Autowired
	GridFsTemplate gridFsTemplate;

	@Override
	public void run(String... args) throws Exception {
		GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is("testing.png")));
		URL url = new URL("https://i.dailymail.co.uk/i/pix/tm/2007/07/lionking1807_468x325._to_468x312jpeg");
		//gridFsTemplate.store(url.openStream(), "lion.jpeg",  "image/jpeg");
	}

}