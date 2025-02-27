package com.min.koreaareaboard.board.entity;

import com.min.koreaareaboard.attachment.entity.Attachment;
import com.min.koreaareaboard.category.entity.Category;
import com.min.koreaareaboard.common.entity.BaseEntity;
import com.min.koreaareaboard.common.enums.CommonStatus;
import com.min.koreaareaboard.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT", length = 3000)
    private String content;

    @Column(name = "views", nullable = false)
    @Builder.Default
    private Long views = 0L;

    @Column(name = "board_status", nullable = false)
    @Builder.Default
    private CommonStatus status = CommonStatus.PUBLIC;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "attachment_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Attachment> attachments;

    @JoinColumn(name = "category_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;
}
