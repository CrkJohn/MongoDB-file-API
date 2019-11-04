package ieti.trello.backend.trello.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import ieti.trello.backend.trello.entities.Task;
import ieti.trello.backend.trello.persistence.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RequestMapping("v1/api")
@RestController
public class RESTController {

    @Autowired
    ITaskRepository  taskRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;
    //TODO inject components (TodoRepository and GridFsTemplate)

    @RequestMapping("/files/{filename}")
    public ResponseEntity<InputStreamResource> getFileByName(@PathVariable String filename) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(filename)));
        if(file != null){
            GridFsResource resource = gridFsTemplate.getResource(file.getFilename());
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(resource.getContentType()))
                    .body(new InputStreamResource(resource.getInputStream()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin("*")
    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println(file.getName()  + " " +  file.getContentType()  );
        gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";
    }

    @CrossOrigin("*")
    @PostMapping("/todo")
    public Task createTodo(@RequestBody Task todo) {
        //TODO implement method
        return null;
    }

    @CrossOrigin("*")
    @GetMapping("/todo")
    public List<Task> getTodoList() {
        //TODO implement method
        return null;
    }

}