package cgg.blogapp.blogapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgg.blogapp.blogapp.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
