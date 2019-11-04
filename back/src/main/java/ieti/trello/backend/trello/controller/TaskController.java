package ieti.trello.backend.trello.controller;

import ieti.trello.backend.trello.entities.*;

import java.util.concurrent.atomic.AtomicLong;

import ieti.trello.backend.trello.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "v1/task")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    @Qualifier("taskService")
    ITaskService iTaskService;

	@GetMapping
	public ResponseEntity<?> geTasksList(){
		try {
			return new ResponseEntity<>(iTaskService.geTasksList(),HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable  String id){
		try {
            return new ResponseEntity<>(iTaskService.getTaskById(id),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    
	}

	@GetMapping("/user/allTask/{userId}")
    public ResponseEntity<?> getTasksByUserId(@PathVariable String userId){
		try {
            return new ResponseEntity<>(iTaskService.getTasksByUserId(userId),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }

	}

    @PostMapping
    public ResponseEntity<?> saveTasks(@RequestBody Task task){
        try {
            System.out.println(task.toString());
            iTaskService.saveTasks(task);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/user/addTask/{taskId}")
    public ResponseEntity<?> assignTaskToUser(String taskId,@RequestBody User user){
		try {
            return new ResponseEntity<>(iTaskService.assignTaskToUser(taskId, user),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    
	}
		
	@DeleteMapping("/removeTask/{taskId}")
    public ResponseEntity<?> removeTask(@PathVariable String taskId){
		try {
			iTaskService.removeTask(taskId)	;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    
	}
	
	@PutMapping("/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody Task task){
		try {
            return new ResponseEntity<>(iTaskService.updateTask(task),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
	}



	
}
