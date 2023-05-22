package com.mbti.domain.repository;

import com.mbti.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>  {

     Optional<User> findByUserNick(String nick);

     List<User> findByIsLoggedInIsTrue();

    // 찾아올 일 있으면 그때 만들기


}
