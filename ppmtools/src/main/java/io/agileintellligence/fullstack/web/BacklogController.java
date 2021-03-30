package io.agileintellligence.fullstack.web;

import io.agileintellligence.fullstack.domain.ProjectTask;
import io.agileintellligence.fullstack.services.MapValidationErrorService;
import io.agileintellligence.fullstack.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult result, @PathVariable String backlog_id) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);

        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }
    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklogs(@PathVariable String backlog_id)
    {
        return projectTaskService.findByBacklogId(backlog_id);
    }
    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectBySequence(@PathVariable String backlog_id,@PathVariable String pt_id)
    {
        ProjectTask projectTask1 = projectTaskService.findByProjectSequence(backlog_id,pt_id);
        return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.OK);
    }
    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectTask projectTask1,@PathVariable String backlog_id,@PathVariable String pt_id,BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null) return errorMap;
        ProjectTask updatedProject = projectTaskService.updateProject(projectTask1,backlog_id,pt_id);
        return new ResponseEntity<ProjectTask>(updatedProject, HttpStatus.OK);
    }
}
