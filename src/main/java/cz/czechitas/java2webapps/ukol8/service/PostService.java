package cz.czechitas.java2webapps.ukol8.service;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import cz.czechitas.java2webapps.ukol8.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<Post> list(Pageable pageable) {

        return postRepository.findAll(pageable);
    }

    public Page<Post> singlePost(String slug, Pageable pageable) {

        return postRepository.jedenPost(slug, pageable);
    }

    public Page<Post> listOlder(Pageable pageable) {
        pageable = PageRequest.of(0, 20);
        LocalDate todayDate = LocalDate.now();
        return postRepository.findByPublishedBeforeAndPublishedIsNotNullOrderByPublishedDesc(todayDate, pageable);
    }
}
