package org.example.BlogProject.repositories;

import org.example.BlogProject.models.Blog;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{
    @Query(value = "SELECT * FROM blog WHERE blog_approved = true ORDER BY blog_creation_time DESC", nativeQuery = true)
    public List <Blog> findByApproved();

    @Query(value = "SELECT * FROM blog WHERE blog_approved = false ORDER BY blog_creation_time DESC", nativeQuery = true)
    public List <Blog> findByUnApprove();


}