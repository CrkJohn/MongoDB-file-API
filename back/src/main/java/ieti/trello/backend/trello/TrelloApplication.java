package ieti.trello.backend.trello;

import com.mongodb.client.gridfs.model.GridFSFile;
import ieti.trello.backend.trello.config.JwtFilter;
import ieti.trello.backend.trello.entities.Task;
import ieti.trello.backend.trello.entities.User;
import ieti.trello.backend.trello.entities.util.State;
import ieti.trello.backend.trello.persistence.ITaskRepository;
import ieti.trello.backend.trello.persistence.IUserRepository;
import ieti.trello.backend.trello.services.IUserService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


	/*
	@Autowired
	GridFsTemplate gridFsTemplate;


	@Autowired
	ITaskRepository iTaskRepository;

	@Autowired
	IUserRepository iUserRepository;

	private static List<User> users;
	private static List<Task> tasks;
	private static String names[] = new String[]{"John","David","Tatiana","Yohanna","Juan"};
	private static String lastNames[] = new String[]{"Ibañez","Rodriguez","Arevalo","Toro","Velandia"};
	private static String namesTask[] = new String[]{"Make API-REST","Learn ML","Make FrontEnd","Learn R2","Google maps"};
	private static String description[] = new String[]{"Create a apit-rest http","learn tensorflow","Make three componenet"
			,"Data science","Make trip in the map"};
	private static Random r = new Random();
	private static int low = 0,  high = 4;


	@Override
	public void run(String... args) throws Exception {
		GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is("testing.png")));
		URL url = new URL("https://i.dailymail.co.uk/i/pix/tm/2007/07/lionking1807_468x325._to_468x312jpeg");
		//gridFsTemplate.store(url.openStream(), "lion.jpeg",  "image/jpeg");
		users = new ArrayList<User>();
		tasks = new ArrayList<Task>();
		for(int i = 0 ; i < 10 ; ++i){
			int idRamdon =  r.nextInt(high-low) + low  , idRamdon2 =  r.nextInt(high-low) + low;
			User user = new User();
			String name = names[idRamdon] + " "  + lastNames[idRamdon2];
			String email = names[idRamdon] + "."  + lastNames[idRamdon2]+"@mail.escuelaing.edu.co";
			user.setUserName(String.valueOf(i+1));
			user.setName(name);
			user.setEmail(email);
			user.setPassword(name);
			iUserRepository.save(user);
		}
		User user = new User();
		user.setUserName("CrkJohn");
		user.setName("John");
		user.setEmail("john@mail.escuelaing.edu.co");
		user.setPassword("123");
		users.add(user);

		for(int i = 0 ; i < 3 ; ++i){
			Task task = new Task();
			task.setId(String.valueOf(i+1));
			task.setDescription(description[i]);
			task.setName(namesTask[i]);
			task.setState(((i%2 == 0 ) ? State.Ready : State.InProgress));
			user = new User();
			user.setName(names[i]);
			user.setEmail(names[i]+"@mail.escuelaing.edu.co");
			user.setUserName(String.valueOf(i+1));
			task.getMembers().add(user);
			task.setExpirationDate("1997-11-10");
			iTaskRepository.save(task);
		}

	}
	*/

}