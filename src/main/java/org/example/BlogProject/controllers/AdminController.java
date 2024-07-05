package org.example.BlogProject.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.example.BlogProject.models.Author;
import org.example.BlogProject.models.Blog;
import org.example.BlogProject.models.Tag;
import org.example.BlogProject.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class AdminController {

    @Autowired
    ServiceLayer service;


    @GetMapping("/getUnapprovedBlogs")
    public ResponseEntity <List<Blog>> getUnapprovedBlogs(){
        List<Blog> blog = service.getUnapprovedBlogs();
        return ResponseEntity.ok(blog);
    }

    @GetMapping("/getAllTag")
    public ResponseEntity <List<Tag>> getTage(){
        List<Tag> tag = service.getTags();
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity <List<Author>> getUsers(){
        List<Author> listUsers = service.getAuthors();
        return ResponseEntity.ok(listUsers);
    }

    @PostMapping("/createTag")
    public ResponseEntity <Tag> createTag(@Valid @RequestBody Tag tag){
        service.saveTag(tag);
        Tag t = service.getTagName(tag.getTagName());
        return ResponseEntity.ok(t);
    }

    @PostMapping("/addTagToBlog/{tagId}/{blogId}")
    public ResponseEntity <Tag> addTagToBlog( @PathVariable Integer tagId, Integer blogId){

        Tag tag = service.getTag(tagId);
        Blog blog = service.getBlog(blogId);

        tag.getBlog().add(blog);

        service.saveTag(tag);
        return ResponseEntity.ok(tag);
    }


    @GetMapping("/getBlogByTag/{tagName}")
    public ResponseEntity <List<Blog>> getBlogByTag(@PathVariable String tagName){
        List<Blog> blog = service.getBlogByTagName(tagName);
        return ResponseEntity.ok(blog);
    }



    @GetMapping("/getApprovedBlogs")
    public ResponseEntity <List<Blog>> getApprovedBlogs(){
        List<Blog> blist = service.getBlogs();
        return ResponseEntity.ok(blist);
    }

    @DeleteMapping ("/deleteBlog/{id}")
    public ResponseEntity <Blog> deleteBlog(@PathVariable Integer id){
        Blog blog = service.getBlog(id);
        service.deleteBlog(id);
        return ResponseEntity.ok(blog);
    }


}

