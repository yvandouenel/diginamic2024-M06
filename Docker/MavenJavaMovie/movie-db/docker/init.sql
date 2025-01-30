CREATE TABLE movies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    screenwriter VARCHAR(255),
    main_actors TEXT
);