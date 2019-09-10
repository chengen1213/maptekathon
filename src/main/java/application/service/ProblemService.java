package application.service;

import application.model.Problem;

import java.util.List;

public interface ProblemService {
    void addProblem(Problem problem);

    Problem findProblemById(long id);

    List<Problem> getAllProblems();
}
