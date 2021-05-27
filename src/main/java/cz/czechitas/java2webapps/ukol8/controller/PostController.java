package cz.czechitas.java2webapps.ukol8.controller;

import cz.czechitas.java2webapps.ukol8.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ModelAndView showAllPosts(Pageable pageable) {
        return new ModelAndView("index")
                .addObject("posts",postService.list(pageable));
    }


    @GetMapping("/{slug}")
    public Object showSinglePost(@PathVariable("slug") String slug, Pageable pageable) {
        return new ModelAndView("index")
                .addObject("posts",postService.singlePost(slug, pageable));


    }


/*    @GetMapping("/test")
    public Object showAllPostsSorted(Pageable pageable) {
        return new ModelAndView("index")
                .addObject("posts",postService.list2(pageable));
    }*/

}