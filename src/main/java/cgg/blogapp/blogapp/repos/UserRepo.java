package cgg.blogapp.blogapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgg.blogapp.blogapp.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByName(String username);
}
