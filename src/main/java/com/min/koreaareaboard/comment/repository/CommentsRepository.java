package com.min.koreaareaboard.comment.repository;

import com.min.koreaareaboard.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
