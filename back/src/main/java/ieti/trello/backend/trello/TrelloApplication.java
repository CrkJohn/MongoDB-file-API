package ieti.trello.backend.trello;

import ieti.trello.backend.trello.config.AppConfiguration;
import ieti.trello.backend.trello.config.JwtFilter;
import ieti.trello.backend.trello.entities.Customer;
import ieti.trello.backend.trello.entities.Task;
import ieti.trello.backend.trello.entities.User;
import ieti.trello.backend.trello.persistence.ICustomerRepository;
import ieti.trello.backend.trello.services.ITaskService;
import ieti.trello.backend.trello.services.IUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TrelloApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("taskServiceMongo")
	ITaskService iTaskService;

	@Autowired
	@Qualifier("userServiceMongo")
	IUserService iUserService;

	@Autowired
	ICustomerRepository iCustomerRepository;

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

	@Override
	public void run(String... args) throws Exception {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
		MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");
		iCustomerRepository.deleteAll();

		iCustomerRepository.save(new Customer("Alice", "Smith"));
		iCustomerRepository.save(new Customer("Bob", "Marley"));
		iCustomerRepository.save(new Customer("Jimmy", "Page"));
		iCustomerRepository.save(new Customer("Freddy", "Mercury"));
		iCustomerRepository.save(new Customer("Michael", "Jackson"));

		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : iCustomerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println("-------------------------------");

		iTaskService.deleteAll();
		iUserService.removeAllUsers();
		for(int i = 0 ; i < 25 ; i++){
			Task task = new Task();
			task.set_id(ObjectId.get());
			task.setName("test number " + String.valueOf(i+1));
			task.setResponsible("responsible"+String.valueOf((int) (Math.random() * 10) + 1));
			task.setPriority((int) (Math.random() * 10) + 1);
			task.setExpirationDate( ( (i<10) ? "1996-10-12" : "2019-10-28" ));
			task.setDescription( (i<10) ? "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" : "aaaaaaaaaa");
			iTaskService.saveTasks(task);
		}
		for(int i = 0 ; i < 10 ; i++){
			User user = new User();
			user.set_id(ObjectId.get());
			user.setName("responsible"+String.valueOf(i+1));
			user.setEmail("responsible"+ String.valueOf(i+1));
			user.setPassword(String.valueOf("12345"));
			iUserService.createUser(user);
		}


		System.out.println("Task found with findByResponsible():");
		System.out.println("-------------------------------");
		System.out.println(iTaskService.findByResponsible("responsible1").size());
		List<Task> tasks = iTaskService.findByResponsible("responsible1");
		for (Task task : tasks) {
			System.out.println(task.toString());
		}
		System.out.println("-------------------------------");


		System.out.println("Task that the dueDate has expire");
		Query query = new Query();
		query.addCriteria(Criteria.where("expirationDate").lt(new SimpleDateFormat("YYYY-MM-dd").format(new Date())));
		tasks = mongoOperation.find(query,Task.class);
		for(Task task : tasks){
			System.out.println(task.toString());
		}
		System.out.println("-------------------------------");

		System.out.println("Task that are assigned to given user and have priority greater equal to 5");
		tasks = iTaskService.findByResponsibleAndPriorityIsGreaterThan("responsible1" , 4);
		for(Task task : tasks){
			System.out.println(task.toString());
		}
		System.out.println("-------------------------------");





		System.out.println("List users that have assigned more than 2 Task");
		List<User> names = iUserService.getUsersList();
		for (User user: names){
			int amount = iTaskService.countTaskByResponsible(user.getName());
			if(amount >=2){
				List<User> users = iUserService.getUserByName(user.getName());
				for (User userPrint : users) {
					System.out.println(userPrint.toString());
				}
			}
		}
		System.out.println("-------------------------------");

		System.out.println("Task list that contains the description with a length greater than 30 characters");
		tasks = iTaskService.findByDescriptionIsGreaterThan(2);
		for (Task task: tasks) {
			System.out.println(task.toString());
		}
		System.out.println("--------------DONT WORKING-----------------");










	}
}