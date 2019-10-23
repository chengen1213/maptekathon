package application.controller;

import application.dto.UploadFileResponse;
import application.exception.MyFileNotFoundException;
import application.model.Data;
import application.service.DataService;
import application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DataController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private DataService dataService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadDataset(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String contentType = file.getContentType();
        Data data = new Data();
        data.setFileName(fileName);
        data.setContentType(contentType);
        dataService.addData(data);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();

        return new UploadFileResponse(data.getId(), fileName, contentType, file.getSize());
    }

    @PostMapping("/dataset/{id}")
    public UploadFileResponse uploadAnswer(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String contentType = file.getContentType();
        Optional<Data> optional = dataService.findDataById(id);
        if (optional.isPresent()) {
            Data data = optional.get();
            data.setAnswerFileName(fileName);
            data.setAnswerContentType(contentType);
            dataService.addData(data);
        }

        return new UploadFileResponse(id, fileName, contentType, file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadDataset(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long id, HttpServletRequest request) {
        // Load file as Resource
        Optional<Data> optional = dataService.findDataById(id);
        if (!optional.isPresent())
            throw new MyFileNotFoundException("Cannot find data: id = " + id);
        Data data = optional.get();
        Resource resource = fileStorageService.loadFileAsResource(data.getFileName());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
