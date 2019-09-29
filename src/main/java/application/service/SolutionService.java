package application.service;

import application.model.Solution;
import application.model.SolutionKey;

public interface SolutionService {
    public Solution addSolution(Solution solution);
    public Solution findSolutionById(SolutionKey solutionKey);
}
