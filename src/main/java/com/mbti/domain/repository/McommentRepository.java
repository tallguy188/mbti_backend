package com.mbti.domain.repository;

import com.mbti.domain.entity.Mcomment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface McommentRepository extends JpaRepository<Mcomment,Integer> {

}
