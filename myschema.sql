DROP DATABASE IF EXISTS media;

CREATE DATABASE media;

USE media;

CREATE TABLE photos {
    m_id INT auto_increment,
    title VARCHAR(255) NOT NULL,
    media_type VARCHAR(255) NOT NULL,
    content mediumblob,
    PRIMARY KEY (m_id)
};

GRANT ALL privileges ON media ;
