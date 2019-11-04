package ieti.trello.backend.trello.database.stub;

import javax.annotation.PostConstruct;

import ieti.trello.backend.trello.database.IDataBase;
import ieti.trello.backend.trello.entities.util.State;
import org.springframework.stereotype.Component;

import ieti.trello.backend.trello.entities.*;
import java.util.*;


@Component("implDataBasesCli")
public class DataBasesCli implements IDataBase {

    private static List<User> users;
    private static List<Task> tasks;
    private static String names[] = new String[]{"John","David","Tatiana","Yohanna","Juan"};
    private static String lastNames[] = new String[]{"Ibañez","Rodriguez","Arevalo","Toro","Velandia"};
    private static String namesTask[] = new String[]{"Make API-REST","Learn ML","Make FrontEnd","Learn R2","Google maps"};
    private static String description[] = new String[]{"Create a apit-rest http","learn tensorflow","Make three componenet"
                                                      ,"Data science","Make trip in the map"};


    private static Random r = new Random();
    private static int low = 0,  high = 4;
   

    public DataBasesCli(){}

    @PostConstruct
    private void init(){
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
            users.add(user);
        }
        User user = new User();
        user.setUserName("CrkJohn");
        user.setName("John");
        user.setEmail("john@mail.escuelaing.edu.co");
        user.setPassword("123");
        users.add(user);

        for(int i = 0 ; i < 4 ; ++i){
            Task task = new Task();
            task.setId(String.valueOf(i+1));
            task.setDescription(description[i]);
            task.setName(namesTask[i]);
            task.setState(((i%2 == 0 ) ? State.Ready : State.InProgress));
            user = new User();
            user.setName(names[i]);
            user.setEmail(names[i]+"@mail.escuelaing.edu.co");
            user.setUserName(String.valueOf(i+1));
            task.setExpirationDate("1997-11-10");
            tasks.add(task);
        }
        System.out.println(tasks.toString());
    }

    @Override
    public List<Task> geTasksList() {
        return tasks;
    }

    @Override
    public Task getTaskById(String id) {
        Task returnTask = null;
        for(Task task : tasks){
            if(task.getId().equals(id)){
                returnTask =  task;
                break;
            }
        }
        return returnTask;
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        List<Task> returnTasks = null;
        for(User user : users){
            if(user.getUserName().equals(userId)){
                returnTasks =  geTasksList();
                break;
            }
        }
        return  returnTasks;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user){
        Task returnTask = null;
        for(Task task : tasks){
            if(task.getId().equals(taskId)){
                returnTask =  task;
                task.getMembers().add(user);
                break;
            }
        }
        return returnTask;
    }

    @Override
    public void removeTask(String taskId) {
        int index = -1;
        for(int i = 0 ; i < tasks.size() ; ++i){
            if(tasks.get(i).getId().equals(taskId)){
                index = i;
                break;
            }
        }
        if(index !=-1){
            tasks.remove(index);
        }
    }

    @Override
    public Task updateTask(Task task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getUsersList() {
        return users;
    }

    @Override
    public User getUserById(String userId) {
        User returnUser = null;
        for(User user : users){
            if(user.getUserName().equals(userId)){
                returnUser =  user;
                break;
            }
        }
        return returnUser;
    }

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        int index = -1;
        for(int i = 0 ; i < users.size() ; ++i){
            if(users.get(i).getUserName().equals(user.getUserName())){
                index = i;
                break;
            }
        }
        if(index != -1){
            users.set(index, user);
        }
        return user;
    }

    @Override
    public void removeUser(String userId) {
        int index = -1;
        for(int i = 0 ; i < users.size() ; ++i){
            if(users.get(i).getUserName().equals(userId)){
                index = i;
                break;
            }
        }
        if(index != -1){
            users.remove(index);
        }
    }

    @Override
    public void saveTask(Task task) {
        tasks.add(task);
    }


}
