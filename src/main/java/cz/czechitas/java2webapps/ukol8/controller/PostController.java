package cz.czechitas.java2webapps.ukol8.controller;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import cz.czechitas.java2webapps.ukol8.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public Object showAllPosts(@PageableDefault(size = 20) Pageable pageable) {

        Page<Post> page = postService.list(pageable);
        //pageable = PageRequest.of(0, 20);
        return new ModelAndView("index")
                .addObject("posts",postService.list(pageable))
                .addObject("page", page);
    }

    @GetMapping("/{slug}")
    public Object showSinglePost(@PathVariable("slug") String slug, Pageable pageable) {
        Page<Post> page = postService.listOlder(pageable);
        return new ModelAndView("index")
                .addObject("posts",postService.singlePost(slug, pageable))
                .addObject("page", page);
    }

    @GetMapping("/onlyolder")
    public Object showOlder(Pageable pageable) {
        Page<Post> page = postService.listOlder(pageable);
        return new ModelAndView("index")
                .addObject("posts",postService.listOlder(pageable))
                .addObject("page", page);
    }

}