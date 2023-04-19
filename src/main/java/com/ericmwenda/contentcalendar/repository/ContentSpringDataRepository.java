package com.ericmwenda.contentcalendar.repository;

import com.ericmwenda.contentcalendar.model.Content;
import com.ericmwenda.contentcalendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//The spring data uses an interface.
//You can use the @Repository Annotation since this is an interface.
//At runtime, spring detects that you are extending one of the base classes,
//It injects automatically and provides all the necessary methods that are needed from the base classes extended.
//Using spring data, CRUD operations are already implement out of the box so you don't have to write any code.
public interface ContentSpringDataRepository extends ListCrudRepository<Content, Integer> {
    // When you extend - Repository you have to provide your own CRUD operation methods..

    //You can extend different classes such as CrudRepository, ListCrudRepository
    //All these classes have CRUD Method operations implemented automatically so no need for crud code -
    //just call the dedicated methods.

    //In the List crud repository you have to give it the name of the class that it's managing and an Integer id.

    //CrudRepository returns Iterables. ListCrudRepository return Lists.

    //The chain followed is
    //ListCrudRepository extends CrudRepository which extends Repository.
    //Crud Repository contains most of the CRUD operation methods.

    //We can create custom methods to find data by using special Spring chaining syntax.
    //This is called query derivation.
    List<Content> findAllByTitleContains(String keyword);
    List<Content> findAllByTitleContainsIgnoreCase(String keyword);

    //Inorder to write your own query with a custom method you can use the @Query annotation to override the query
    //implementation.
    //Just as you use the @PathVariable, to specify a parameter within the query you use the @Param Annotation.
    //providing the parameter name as used.
    @Query("""
    SELECT * FROM Content
    WHERE status = :status
    """)
    List<Content> findAllByStatus(@Param("status") Status status);
}
