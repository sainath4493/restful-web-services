package com.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfulwebservices.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
