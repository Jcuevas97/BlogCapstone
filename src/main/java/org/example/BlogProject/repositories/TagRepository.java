package org.example.BlogProject.repositories;

import org.example.BlogProject.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TagRepository extends JpaRepository<Tag, Integer>{
    @Query(value = "SELECT * FROM tag WHERE tag_name = ?1", nativeQuery = true)
    public Tag getTagByName(String tagName);

    @Query(value = "DELETE * FROM blog_tag WHERE blog_id = ?1", nativeQuery = true)
    public Tag deleteByBlogId(int id);
}
