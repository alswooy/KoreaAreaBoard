package com.min.koreaareaboard.utill;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false, name = "reg_date")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "up_date")
    private LocalDateTime upDate;

    @CreatedBy
    @Column(updatable = false, name = "reg_seq")
    private Long regSeq;

    @LastModifiedBy
    @Column(name = "up_seq")
    private Long upSeq;
}

