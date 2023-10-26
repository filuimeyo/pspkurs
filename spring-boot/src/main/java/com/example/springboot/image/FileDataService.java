package com.example.springboot.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileDataService {
    private final FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "D:/Desktop/uploadspspkurs/";

    public FileDataService(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    public String uploadImageFile(MultipartFile file) throws IOException {


        String filename = UUID.randomUUID().toString() + ".jpg";
        Path saveTO = Paths.get(FOLDER_PATH + filename);
        // String filePath = FOLDER_PATH + file.getOriginalFilename();
        // String filePath = FOLDER_PATH + saveTO;

        FileData fileData = fileDataRepository.save(FileData.builder()
                //.name(file.getOriginalFilename())
                .name(filename)
                .type(file.getContentType())
                .filePath(String.valueOf(saveTO))
                .build()
        );
        //file.transferTo(new File(filePath));

        Files.copy(file.getInputStream(), saveTO);

        if (fileData != null) {
            return "file uploaded successfully: " + saveTO;
        }
        return null;

    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> im = fileDataRepository.findByName(fileName);
        //String filePath = im.get().getFilePath();

        byte[] images = Files.readAllBytes(
                  new File( FOLDER_PATH + im.get().getName()).toPath());

        return images;
    }

    public void deleteImage(long id) throws IOException {
       FileData fileData = fileDataRepository.findById(id)
               .orElseThrow(()-> new IllegalStateException(
                       "image with id " + id + " does not exist"
               ));
       String fileName = FOLDER_PATH + fileData.getName();
       Files.deleteIfExists(Path.of(fileName));
       fileDataRepository.deleteById(id);

    }

    public void updateImage(long id, MultipartFile file) throws IOException {
        FileData fileData = fileDataRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "image with id " + id + " does not exist"
                ));

        Path path = Path.of(FOLDER_PATH + fileData.getName());
        Files.deleteIfExists(path);
        Files.copy(file.getInputStream(), path);

    }

    public List<FileData> getAll() {
        return  fileDataRepository.findAll();
    }
}
