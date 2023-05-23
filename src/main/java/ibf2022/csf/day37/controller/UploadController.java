package ibf2022.csf.day37.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.csf.day37.model.Photo;
import ibf2022.csf.day37.repository.PhotoRepository;
import ibf2022.csf.day37.service.PhotoService;

@Controller
@RequestMapping
public class UploadController {
    
    @Autowired
    private PhotoService photoService;

    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postUpload(@RequestPart String title, @RequestPart MultipartFile mypic, Model model) {

        boolean result = photoService.upload(title, mypic);

        model.addAttribute("filename", mypic.getOriginalFilename());
        model.addAttribute("size", mypic.getSize());
        model.addAttribute("contentType", mypic.getContentType());
        model.addAttribute("uploaded", result);
        return "uploaded";
    }

    @GetMapping(path = "/photo/{photoId}")
    @ResponseBody
    public ResponseEntity<byte[]> getPhoto(@PathVariable Integer photoId) {

        Optional<Photo> photo = photoRepository.findPhotoById(photoId);

        if (photo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Photo p = photo.get();

        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", p.contentType()).body(p.content());
    }
}
