package application.service;

import application.model.Solution;
import application.model.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SolutionServiceImpl implements SolutionService{

    @Autowired
    SolutionRepository solutionRepository;

    @Autowired
    CodeExecutionService codeExecutionService;

    @Override
    public Solution addSolution(Solution solution) {
        codeExecutionService.execute(solution);
//        solutionRepository.save(solution);
        return solution;
    }
}
