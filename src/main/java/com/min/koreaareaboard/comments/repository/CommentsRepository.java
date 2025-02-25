package com.min.koreaareaboard.comments.repository;

import com.min.koreaareaboard.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
