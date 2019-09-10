package application.controller;

import application.model.Problem;
import application.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    ResponseEntity<?> getProblems() {
        List<Problem> problems = problemService.getAllProblems();
        return ResponseEntity.ok(problems);
    }
}
