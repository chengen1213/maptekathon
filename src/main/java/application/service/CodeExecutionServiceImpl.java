package application.service;

import application.exception.FileStorageException;
import application.model.Data;
import application.model.Problem;
import application.model.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.UUID;

@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public void execute(Solution solution) {
        Problem problem = solution.getProblem();
        Collection<Data> publicDataset = problem.getPublicDataset();
        Collection<Data> privateDataset = problem.getPrivateDataset();
        String fileName = UUID.randomUUID().toString() + "." + solution.getLanguage();
        String code = solution.getCode();

        final Path targetLocation = fileStorageService.getFileStorageLocation();
        Path codeLocation = targetLocation.resolve(fileName);
        File file = codeLocation.toFile();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(code);
            writer.close();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
