CREATE TABLE IF NOT EXISTS Content (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(255)
);

/*INSERT INTO Content(title, description, status, content_type, date_created, date_updated)
VALUES('SQL Blog Post, Connect to SQL', 'An article to help you connect to sql.', 'PUBLISHED', 'ARTICLE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO Content(title, description, status, content_type, date_created, date_updated, url)
VALUES('Second SQL Blog Post, Connect to SQL', 'A Second article to help you connect to sql.', 'IN_PROGRESS', 'VIDEO',
       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'http://127.0.0.1:8080/api/v1/content/getRandomMath');*/