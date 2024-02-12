package cgg.blogapp.blogapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgg.blogapp.blogapp.entities.Category;
import cgg.blogapp.blogapp.entities.Post;
import cgg.blogapp.blogapp.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    // List<Post> findByposttitontaining(String title);

    List<Post> findByPosttitleContaining(String posttitle);
}
