package com.min.koreaareaboard.board.entity;

import com.min.koreaareaboard.category.entity.Category;
import com.min.koreaareaboard.likes.entity.Likes;
import com.min.koreaareaboard.user.entity.User;
import com.min.koreaareaboard.utill.BaseEntity;
import com.min.koreaareaboard.utill.CommonStatus;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "board")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Board extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private Long views;

    @Column(name = "board_status")
    private CommonStatus status;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "like_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Likes likes;

    @JoinColumn(name = "category_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;
}
