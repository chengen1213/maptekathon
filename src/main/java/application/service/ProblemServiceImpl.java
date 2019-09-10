package application.service;

import application.model.Problem;
import application.model.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public void addProblem(Problem problem) {
        problemRepository.save(problem);
    }

    @Override
    public Problem findProblemById(long id) {
        return problemRepository.getOne(id);
    }

    @Override
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }
}
