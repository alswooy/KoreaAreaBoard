package com.min.koreaareaboard.like.repository;

import com.min.koreaareaboard.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Like, Long> {
}
