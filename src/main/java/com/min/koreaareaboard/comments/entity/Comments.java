package com.min.koreaareaboard.comments.entity;

import com.min.koreaareaboard.board.entity.Board;
import com.min.koreaareaboard.comments.service.CommentService;
import com.min.koreaareaboard.user.entity.User;
import com.min.koreaareaboard.utill.BaseEntity;
import com.min.koreaareaboard.utill.CommonStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Comments extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String content;

    @Column(name = "comment_status")
    private CommonStatus commentStatus;

}
