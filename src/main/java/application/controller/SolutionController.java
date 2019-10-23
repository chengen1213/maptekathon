package application.controller;

import application.dto.SolutionDto;
import application.exception.GeneralException;
import application.model.Problem;
import application.model.Solution;
import application.model.SolutionKey;
import application.model.User;
import application.service.CodeExecutionService;
import application.service.ProblemService;
import application.service.SolutionService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SolutionController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private CodeExecutionService codeExecutionService;

    @GetMapping("/problems/{problemId}/solution")
    public Solution submitSolution(Principal principal, @PathVariable Long problemId) {
        String userName = principal.getName();
        User user = userService.findByUserEmail(userName);
        SolutionKey solutionKey = new SolutionKey(user.getId(), problemId);
        return solutionService.findSolutionById(solutionKey);
    }

    @GetMapping("/problems/{problemId}/solutions")
    public List<Solution> getSolutions(Principal principal, @PathVariable Long problemId) {
//        String userName = principal.getName();
//        User user = userService.findByUserEmail(userName);
//        SolutionKey solutionKey = new SolutionKey(user.getId(), problemId);
        Problem problem = problemService.findProblemById(problemId);
        return new ArrayList<>(problem.getSolutions());
    }

    @PostMapping("/problems/{id}/solutions")
    public Solution submitSolution(Principal principal, @PathVariable Long id, @RequestBody SolutionDto solutionDto) {
//        String userName = principal.getName();
        String userName = "enawaken@gmail.com";
        User user = userService.findByUserEmail(userName);
        SolutionKey solutionKey = new SolutionKey(user.getId(), id);
        Problem problem = problemService.findProblemById(id);
        if (problem.getLanguage() != null && !problem.getLanguage().equals(solutionDto.getLanguage()))
            throw new GeneralException("Accept " + problem.getLanguage() + " only!");
        Solution solution = new Solution();
        solution.setId(solutionKey);
        solution.setUser(user);
        solution.setProblem(problem);
        solution.setLanguage(solutionDto.getLanguage());
        solution.setCode(solutionDto.getCode());
        solution.setFailedPublic(new ArrayList<>());
        solution.setFailedPrivate(new ArrayList<>());
        codeExecutionService.execute(solution);
        solutionService.addSolution(solution);
        return solution;
    }
}
