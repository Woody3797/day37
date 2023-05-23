package ibf2022.csf.day37.repository;

public class DBQueries {
    
    public static final String SQL_SAVE_PHOTO = """
            insert into photos(title, media_type, content) values (?, ?, ?)
            """;

    public static final String SQL_GET_PHOTO_BY_ID = """
            select * from photos where m_id = ?
            """;
}
