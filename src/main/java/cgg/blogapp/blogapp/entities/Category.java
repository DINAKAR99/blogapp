package cgg.blogapp.blogapp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int categoryId;

  @Column(length = 100)
  private String categoryTitle;

  private String categoryDescription;
  @JsonIgnore
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  public List<Post> posts;
}
