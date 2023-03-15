package com.lecongtien.cinema.service;

import com.lecongtien.cinema.model.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImp implements FileUploadService{
    private Path rootPath;

    public FileUploadServiceImp(FileStorageProperties fileStorageProperties) throws IOException {
        // Định nghĩa đường dẫn root
        this.rootPath = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        if(Files.notExists(this.rootPath)){
            // Tạo founder lưu file nếu không tồn tại founder
            Files.createDirectories(rootPath);
        }
    }
    @Override
    public boolean storeFile(MultipartFile file) {
        if(file!= null){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try{
                Files.copy(file.getInputStream(),rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                return true;
            }catch (Exception ex){
                System.out.println("Loi save file nhe " + ex.getMessage());
                return false;
            }
        }
        return false;
    }

    @Override
    public Resource loadFileByName(String fileName) {
        try{
            Path path = this.rootPath.resolve(fileName).normalize();
            // byte[]
            // base 64
            // resource
            Resource resource = new UrlResource((path.toUri()));
            if(resource.exists()){
                return resource;
            }
        }
        catch (Exception ex){
            System.out.println("Lỗi đoc file "+ ex.getMessage());
        }
        return null;
    }
}
