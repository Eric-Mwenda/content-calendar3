package com.ericmwenda.contentcalendar.repository;

import com.ericmwenda.contentcalendar.model.ContentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {
    public final JdbcTemplate jdbcTemplate;

    @Autowired //This is redundant though.
    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //This is to be used as a mapper to map the sql columns to the respective Content object fields.
    private static ContentJdbc mapRow(ResultSet resultSet, int rowNumber) throws SQLException{

        return new ContentJdbc(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("status"),
                resultSet.getString("content_type"),
                resultSet.getTimestamp("date_created"),
                resultSet.getTimestamp("date_updated").toLocalDateTime(),
                resultSet.getString("url")
        );
    }

    //Method to get all the content. Will return a mapped list of Content.
    public List<ContentJdbc> getAllContent(){
        String sql = "SELECT * FROM Content";
        List<ContentJdbc> contents = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
        return contents;
    }

    public void createContent(String title, String description, String status, String contentType, String url){
        String sql =
                "INSERT INTO Content(title, description, status, content_type, date_created, date_updated, url) VALUES(?, ?, ?, ?, NOW(), NOW(), ?);";
        jdbcTemplate.update(sql, title, description, status, contentType, url);
    }

    public void updateContent(int id, String title, String description, String status, String contentType, String url){
        String sql = "UPDATE Content SET title=?, description=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?;";
        jdbcTemplate.update(sql, title, description, status, contentType, url, id);
    }

    public void deleteContent(int id){
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public ContentJdbc getContentById(int id){
        String sql = "SELECT * FROM Content WHERE id=?";
        ContentJdbc content = jdbcTemplate.queryForObject(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
        return content;
    }

    public boolean contentByIdExists(int id){
        return getContentById(id) != null;
    }
}
