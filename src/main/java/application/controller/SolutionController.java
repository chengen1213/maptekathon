package application.controller;

import application.dto.SolutionDto;
import application.model.Problem;
import application.model.Solution;
import application.service.ProblemService;
import application.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private ProblemService problemService;

    @PostMapping("/problems/{id}/solutions")
    public ResponseEntity<?> submitSolution(Principal principal, @PathVariable Long id, @RequestBody SolutionDto solutionDto) {
        String userName = principal.getName();
        Problem problem = problemService.findProblemById(id);
        Solution solution = new Solution();
        solution.setProblem(problem);
        solution.setLanguage(solutionDto.getLanguage());
        solution.setCode(solutionDto.getCode());

        solutionService.addSolution(solution);

        return null;
    }
}
