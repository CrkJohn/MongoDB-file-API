package ieti.trello.backend.trello;

import ieti.trello.backend.trello.entities.Task;
import ieti.trello.backend.trello.entities.util.State;
import ieti.trello.backend.trello.persistence.ITaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoTest {

	@Autowired

	private ITaskRepository iTaskRepository;

	@Test
	public void findByResponsibleTest(){

		iTaskRepository.deleteAll();
		String names[] = new String[]{"Test 1", "Test 2","Test 3"};
		String responsibles[] =  new String[]{"ThisTest", "JohnDavid 2","JohnDavid 3"};
		for(int i = 0 ; i < 3 ; i++ ){
			Task task = new Task();
			task.setState(State.Ready);
			task.setResponsible(responsibles[i]);
			task.setName(names[i]);
			iTaskRepository.save(task);
		}
		List<Task> tasks = iTaskRepository.findByResponsible("ThisTest");
		System.out.println();
		Assert.assertEquals(1, tasks.size());
		Assert.assertEquals("Test 1" , tasks.get(0).getName());
	}

}