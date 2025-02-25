package com.min.koreaareaboard.user.repository;


import com.min.koreaareaboard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository{

}
