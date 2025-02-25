package com.min.koreaareaboard.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardMybatisRepository implements BoardRepository{
    private final BoardMybatisMapper boardMapper;
}
