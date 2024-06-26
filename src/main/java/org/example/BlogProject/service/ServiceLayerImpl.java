package org.example.BlogProject.service;

import org.example.BlogProject.models.Author;
import org.example.BlogProject.models.Blog;
import org.example.BlogProject.models.Tag;
import org.example.BlogProject.repositories.AuthorRepository;
import org.example.BlogProject.repositories.BlogRepository;
import org.example.BlogProject.repositories.TagRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ServiceLayerImpl implements ServiceLayer{

    @Autowired
    BlogRepository blogs;

    @Autowired
    AuthorRepository authors;

    @Autowired
    TagRepository tags;


    @Override
    public List<Blog> getBlogs()  {return blogs.findByApproved();}

    @Override
    public List<Author> getAuthors() {
        return authors.findAll();
    }

    @Override
    public List<Tag> getTags() {
        return tags.findAll();
    }

    @Override
    public Tag getTag(int id) {
        return tags.getById(id);
    }

    @Override
    public Tag getTagName(String name) {
        return tags.getTagByName(name);
    }

    @Override
    public Blog getBlog(int id) {
        return blogs.getById(id);
    }

    @Override
    public void saveBlog(Blog blog) {
        blogs.save(blog);
    }

    @Override
    public void saveAuthor(Author author) {
        authors.save(author);
    }

    @Override
    public void saveTag(Tag tag) {
        tags.save(tag);
    }


    @Override
    public void deleteBlog(int blogId) {
        Blog deleteBlog = blogs.findById(blogId).orElse(null);

        List<Tag> tg = tags.findAll();

        List<Blog> temp;

        for(Tag tag : tg){
            temp = tag.getBlog();
            if(temp.contains(deleteBlog)){
                temp.remove(deleteBlog);
            }
        }



        blogs.deleteById(blogId);
    }

    @Override
    public List<Blog> getUnfilteredBlogs() {
        return blogs.findAll();
    }

    @Override
    public Author getAuthorEmail(String email) {
        return authors.findByEmail(email);
    }

    @Override
    public List<Blog> getUnapprovedBlogs() {
        return blogs.findByUnApprove();
    }
}