package org.example.BlogProject.service;

import org.example.BlogProject.models.Author;
import org.example.BlogProject.models.Blog;
import org.example.BlogProject.models.Tag;
import java.util.List;


public interface ServiceLayer {
    public List<Blog> getBlogs();
    public List<Blog> getUnfilteredBlogs();
    public List<Blog> getUnapprovedBlogs();
    public List<Author> getAuthors();
    public List<Tag> getTags();
    public Tag getTag(int id);
    public Tag getTagName(String name);
    public Blog getBlog(int id);
    public Author getAuthorEmail(String email);
    public void saveBlog(Blog blog);
    public void saveAuthor(Author author);
    public void saveTag(Tag tag);
    public void deleteBlog(int blogId);
}