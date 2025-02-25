package com.min.koreaareaboard.attachments.entity;

import com.min.koreaareaboard.board.entity.Board;
import com.min.koreaareaboard.comments.entity.Comments;
import com.min.koreaareaboard.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attachments")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class attachments extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comments comments;

    private String file_url;
    private String file_name;
}
