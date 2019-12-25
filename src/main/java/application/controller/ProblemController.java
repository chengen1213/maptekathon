package application.controller;

import application.dto.CommonResponse;
import application.dto.ProblemDto;
import application.exception.DataNotFoundException;
import application.exception.GeneralException;
import application.model.Data;
import application.model.Problem;
import application.service.DataService;
import application.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/problems", method = RequestMethod.POST)
    ResponseEntity<?> addProblem(@RequestBody ProblemDto problemDto, HttpServletRequest request) {
        Problem problem = new Problem();
        setProblemContent(problem, problemDto);
        problemService.addProblem(problem);
        return ResponseEntity.ok(problem);
    }

    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    ResponseEntity<?> getProblems() {
        List<Problem> problems = problemService.getAllProblems();
        return ResponseEntity.ok(problems);
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getProblemById(@PathVariable Long id) {
        Problem problem = problemService.findProblemById(id);
        return ResponseEntity.ok(problem);
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateProblemById(@PathVariable Long id, @RequestBody ProblemDto problemDto) {
        Problem problem = problemService.findProblemById(id);
        if (problem == null)
            throw new GeneralException("Question not found!");
        setProblemContent(problem, problemDto);
        problemService.addProblem(problem);
        return ResponseEntity.ok(problem);
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProblemById(@PathVariable Long id) {
        problemService.deleteProblemById(id);
        return ResponseEntity.ok(new CommonResponse("Delete problem " + id + " successfully!"));
    }

    private void setProblemContent(Problem problem, ProblemDto problemDto) {
        problem.setTitle(problemDto.getTitle());
        problem.setDescription(problemDto.getDescription());
        problem.setLanguage(problemDto.getLanguage());
        problem.setCreationDate(new Date());
        problem.setExpirationDate(problemDto.getExpirationDate());
        List<Data> publicDataSet = new ArrayList<>();
        List<Data> privateDataSet = new ArrayList<>();
        for (long id : problemDto.getPublicDataset()) {
            Optional<Data> data = dataService.findDataById(id);
            if (!data.isPresent())
                throw new DataNotFoundException("Could not find data ID = " + id);
            publicDataSet.add(data.get());
        }
        for (long id : problemDto.getPrivateDataset()) {
            Optional<Data> data = dataService.findDataById(id);
            if (!data.isPresent())
                throw new DataNotFoundException("Could not find data ID = " + id);
            privateDataSet.add(data.get());
        }
        if (publicDataSet.size() == 0 || privateDataSet.size() == 0)
            throw new GeneralException("A problem requires at least a public dataset and a private dataset!");
        problem.setPublicDataset(publicDataSet);
        problem.setPrivateDataset(privateDataSet);
    }
}
