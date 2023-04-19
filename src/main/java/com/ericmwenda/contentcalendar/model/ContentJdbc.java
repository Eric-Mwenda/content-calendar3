package com.ericmwenda.contentcalendar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//@Table(value = "table_content")//You can use this annotation to change the table name. Default name is Content.

public record ContentJdbc (
    @Id
    Integer id,
    @NotBlank //Validation annotation to ensure it's not whitespace.
    String title,
    @NotNull //Validation annotation to ensure that it's not null.
    String description,
    //@NotEmpty
    //@Column("col_status")//annotation can be used to override the column name of any property.
    String status,
    String contentType,
    Timestamp dateCreated,
    @PastOrPresent //The annotated element must be an instant, date or time in the present or in the future.
    LocalDateTime dateUpdated,
    String url
){}
