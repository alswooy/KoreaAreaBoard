package com.min.koreaareaboard.likes.repository;

import com.min.koreaareaboard.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
}
