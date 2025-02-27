package com.min.koreaareaboard.like.entity;

import com.min.koreaareaboard.board.entity.Board;
import com.min.koreaareaboard.comment.entity.Comment;
import com.min.koreaareaboard.user.entity.User;
import com.min.koreaareaboard.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Like extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;
}
