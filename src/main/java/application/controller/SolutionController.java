package application.controller;

import application.dto.SolutionDto;
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

    @GetMapping("/problems/{id}/solutions")
    public Solution submitSolution(Principal principal, @PathVariable Long id) {
        String userName = principal.getName();
        User user = userService.findByUserEmail(userName);
        SolutionKey solutionKey = new SolutionKey(user.getId(), id);
        return solutionService.findSolutionById(solutionKey);
    }

    @PostMapping("/problems/{id}/solutions")
    public Solution submitSolution(Principal principal, @PathVariable Long id, @RequestBody SolutionDto solutionDto) {
//        String userName = principal.getName();
        String userName = "enawaken@gmail.com";
        User user = userService.findByUserEmail(userName);
        SolutionKey solutionKey = new SolutionKey(user.getId(), id);
        Problem problem = problemService.findProblemById(id);
        Solution solution = new Solution();
        solution.setId(solutionKey);
        solution.setUser(user);
        solution.setProblem(problem);
        solution.setLanguage(solutionDto.getLanguage());
        solution.setCode(solutionDto.getCode());
        codeExecutionService.execute(solution);
        solutionService.addSolution(solution);
        return solution;
    }
}
