package cz.czechitas.java2webapps.ukol8.repository;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT o FROM Post o")
    Page<Post> vsechnyPosty(Pageable pageable);

    @Query("SELECT o FROM Post o WHERE o.slug = :slug")
    Page<Post> jedenPost(String slug, Pageable pageable);

    Page<Post> findAllByPublishedBeforeAndPublishedIsNotNullOrderByPublished(Pageable pageable);

}
