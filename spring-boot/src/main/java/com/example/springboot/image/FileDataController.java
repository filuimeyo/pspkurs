package com.example.springboot.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registration/image")
public class FileDataController {

    private final FileDataService fileDataService;

    public FileDataController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }


    @PostMapping
    public ResponseEntity<?> uploadImage(
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        String uploadImage = fileDataService.uploadImageFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);

    }

    @DeleteMapping(path="/{id}")
    public void deleteImage(
            @PathVariable Long id
    ) throws IOException {
       fileDataService.deleteImage(id);
    }


    @PutMapping(path="/{id}")
    public void updateImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        fileDataService.updateImage(id, file);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> uploadImage(
            @PathVariable String fileName
    ) throws IOException {
        byte[] im = fileDataService.downloadImageFromFileSystem(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(im);

    }

    @GetMapping()
    public List<FileData> getFileData(){

        return fileDataService.getAll();
    }


}


