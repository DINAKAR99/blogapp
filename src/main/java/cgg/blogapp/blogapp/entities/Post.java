package cgg.blogapp.blogapp.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postid;
    @Column(name = "post_title", length = 100, nullable = false)
    private String posttitle;
    @Column(length = 10000)
    private String postcontent;

    private String imageName;

    @Temporal(value = TemporalType.TIMESTAMP)
    public LocalDateTime addedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    public Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    public User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

}
