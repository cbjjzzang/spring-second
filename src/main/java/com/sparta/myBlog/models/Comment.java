package com.sparta.myBlog.models;

import com.sparta.myBlog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long contentsId;

    public Comment(CommentRequestDto requestDto, String username) {
        this.contents = requestDto.getContents();
        this.contentsId = requestDto.getContentsId();
        this.username = username;
    }

    public long update (CommentRequestDto requestDto){
        this.contents = requestDto.getContents();
        this.contentsId = requestDto.getContentsId();
        return commentId;
    }
}
