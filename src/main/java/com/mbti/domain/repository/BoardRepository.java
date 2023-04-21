package com.mbti.domain.repository;

import com.mbti.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {


}
