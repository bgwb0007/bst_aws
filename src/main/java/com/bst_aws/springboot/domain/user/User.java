package com.bst_aws.springboot.domain.user;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    @Column
    private String picture;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;
    
    @Builder
    public User(Long id, String name, String email, String picture, Role role){
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }
    
    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }
    
    public String getRoleKey(){
        return this.role.getKey();
    }
    
}
