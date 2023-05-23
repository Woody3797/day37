package ibf2022.csf.day37.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.csf.day37.repository.PhotoRepository;

@Service
public class PhotoService {
    
    @Autowired
    private PhotoRepository photoRepository;

    public boolean upload(String title, MultipartFile multipart) {
        
        try {
            return photoRepository.save(title, multipart.getContentType(), multipart.getBytes());
        } catch (DataAccessException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
