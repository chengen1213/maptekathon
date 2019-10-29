package application.service;

import application.exception.CanNotCompileException;
import application.exception.ExecutionErrorException;
import application.model.Data;
import application.model.Problem;
import application.model.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    private final String exeCommand = "python";
    private final String profilerName = "ProgramProfiler.py";

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public void execute(Solution solution) throws ExecutionErrorException {
        Problem problem = solution.getProblem();
        Collection<Data> publicDataset = problem.getPublicDataset();
        Collection<Data> privateDataset = problem.getPrivateDataset();
        String fileId = "Solution";
        String ext = solution.getLanguage();
        String code = solution.getCode();
        String fileName = fileId + "." + ext;

        final Path targetLocation = fileStorageService.getFileStorageLocation();
        Path dirPath = targetLocation.resolve("User" + solution.getUser().getId());
        File dir = new File(dirPath.toString());

        if (!dir.exists())
            dir.mkdir();

        Path codeLocation = dirPath.resolve(fileName);
        File file = codeLocation.toFile();
        try {
            copyFile(targetLocation, dirPath, profilerName);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(code);
            writer.close();

            for (Data data : publicDataset) {
                copyFile(targetLocation, dirPath, data.getFileName());
                if (data.getAnswerFileName() != null)
                    copyFile(targetLocation, dirPath, data.getAnswerFileName());
            }
            for (Data data : privateDataset) {
                copyFile(targetLocation, dirPath, data.getFileName());
                if (data.getAnswerFileName() != null)
                    copyFile(targetLocation, dirPath, data.getAnswerFileName());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            executeProgram(fileId, ext, dirPath.toFile(), solution);
        } catch (Exception e) {
            throw new ExecutionErrorException(e.getMessage());
        }
    }

    private void copyFile(Path source, Path dest, String fileName) throws IOException {
        Files.copy(source.resolve(fileName), dest.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    private void executeProgram(String sourcecodeFileName, String ext, File dir, Solution solution) throws Exception {

        if ("java".equals(ext)) {
            //javac name.java
            String[] javac = {"javac", sourcecodeFileName + "." + ext};
            executeCommand(javac, dir);
        } else if ("cpp".equals(ext)) {
            //gcc –o name name.cpp
            String[] gcc = {"g++", "-o", sourcecodeFileName, sourcecodeFileName + "." + ext};
            executeCommand(gcc, dir);
        } else if ("cs".equals(ext)) {
            //csc name.cs
            String[] csc = {"csc", sourcecodeFileName + "." + ext};
            executeCommand(csc, dir);
        }
        for (Data data : solution.getProblem().getPublicDataset()) {
            String answerFileName = data.getAnswerFileName() == null ? "" : data.getAnswerFileName();
            String[] command = {exeCommand, profilerName, ext, sourcecodeFileName, data.getFileName(), answerFileName};
            String[] info = callProfiler(command, dir);
            if ("False".equals(info[2]))
                solution.getFailedPublic().add(data.getId());
            solution.setPublicSpaceComplexity(solution.getSpaceComplexity() + Double.parseDouble(info[3]));
            solution.setPublicTimeComplexity(solution.getTimeComplexity() + Double.parseDouble(info[4]));
        }
        for (Data data : solution.getProblem().getPublicDataset()) {
            String answerFileName = data.getAnswerFileName() == null ? "" : data.getAnswerFileName();
            String[] command = {exeCommand, profilerName, ext, sourcecodeFileName, data.getFileName(), answerFileName};
            String[] info = callProfiler(command, dir);
            if ("False".equals(info[2]))
                solution.getFailedPublic().add(data.getId());
            solution.setSpaceComplexity(solution.getSpaceComplexity() + Double.parseDouble(info[3]));
            solution.setTimeComplexity(solution.getTimeComplexity() + Double.parseDouble(info[4]));
        }
        double privateCount = solution.getProblem().getPrivateDataset().size();
        double failCount = solution.getFailedPrivate().size();
        solution.setAccuracy(privateCount - failCount / privateCount);
    }

    private void executeCommand(String[] command, File dir) throws Exception {
        ProcessBuilder execute = new ProcessBuilder(command).directory(dir);
//        processBuilder.redirectErrorStream(true);
        Process pr = execute.start();
        pr.waitFor();
        String errMessage = getMessage(pr.getErrorStream());
        if (errMessage != null && !errMessage.trim().equals(""))
            throw new CanNotCompileException(errMessage);
    }

    private String[] callProfiler(String[] command, File dir) throws Exception {
        ProcessBuilder execute = new ProcessBuilder(command).directory(dir);
        Process pr = execute.start();
        pr.waitFor();
        String message;
        if (pr.exitValue() != 0) {
            message = getMessage(pr.getErrorStream());
            throw new ExecutionErrorException(message);
        }
        message = getMessage(pr.getInputStream());
        String[] info = message.split("\\s+");
        if ("False".equals(info[0])) {
            throw new ExecutionErrorException(info[1]);
        }
        return info;
    }

    private String getMessage(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String message = "";
        while ((line = in.readLine()) != null) {
            message += line;
        }
        in.close();
        return message;
    }
}
