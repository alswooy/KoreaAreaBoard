package com.min.koreaareaboard.reports.entity;

import com.min.koreaareaboard.board.entity.Board;
import com.min.koreaareaboard.comments.entity.Comments;
import com.min.koreaareaboard.reports.ReportStatus;
import com.min.koreaareaboard.user.entity.User;
import com.min.koreaareaboard.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "report")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Reports extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comments comments;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus status;
}
