package com.min.koreaareaboard.comment.entity;

import com.min.koreaareaboard.attachment.entity.Attachment;
import com.min.koreaareaboard.board.entity.Board;
import com.min.koreaareaboard.user.entity.User;
import com.min.koreaareaboard.common.entity.BaseEntity;
import com.min.koreaareaboard.common.enums.CommonStatus;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @JoinColumn(name = "attachment_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Attachment> attachments;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT", length = 1000)
    private String content;

    @Column(name = "comment_status", nullable = false)
    @Builder.Default
    private CommonStatus commentStatus = CommonStatus.PUBLIC;

}
