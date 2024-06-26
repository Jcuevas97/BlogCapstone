package org.example.BlogProject.controllers;



import org.example.BlogProject.models.Author;
import org.example.BlogProject.models.Blog;
import org.example.BlogProject.models.Tag;
import org.example.BlogProject.security.CustomUserDetails;
import org.example.BlogProject.service.ServiceLayer;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Controller
public class BlogsController {

    @Autowired
    ServiceLayer service;


    @PostMapping("/createBlog")
    public ResponseEntity <Blog> addBlog(@Valid @RequestBody Blog blog) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof CustomUserDetails) {
            username = ((CustomUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Author author = service.getAuthorEmail(username);

        blog.setAuthor(author);
        blog.setBlogCreationTime(LocalDateTime.now());

        service.saveBlog(blog);
        Blog newBlog = service.getBlog(blog.getId());
        return ResponseEntity.ok(newBlog);
    }


    @GetMapping("/viewBlog/{id}")
    public ResponseEntity <Blog> viewBlog(@PathVariable Integer id) {
        Blog blog = service.getBlog(id);
        return ResponseEntity.ok(blog);
    }


}


