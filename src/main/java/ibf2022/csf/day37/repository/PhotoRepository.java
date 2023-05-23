package ibf2022.csf.day37.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.csf.day37.model.Photo;

@Repository
public class PhotoRepository {
    
    @Autowired
    private JdbcTemplate template;


    public boolean save(String title, String contentType, byte[] content) {

        return template.update(DBQueries.SQL_SAVE_PHOTO, title, contentType, content) > 0;
    }

    public Optional<Photo> findPhotoById(Integer photoId) {

        Optional<Photo> photo = template.query(DBQueries.SQL_GET_PHOTO_BY_ID, rs -> {
            if (!rs.next()) {
                return Optional.empty();
            } else {
                Photo p = new Photo(photoId, rs.getString("title"), rs.getString("media_type"), rs.getBytes("content"));
                return Optional.of(p);
            }
        }, photoId);
        
        return photo;
    }
}
