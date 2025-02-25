package com.min.koreaareaboard.board.repository;

import com.min.koreaareaboard.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Long>, BoardRepository {

}
