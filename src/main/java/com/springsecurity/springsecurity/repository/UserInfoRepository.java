package com.springsecurity.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.springsecurity.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
	 Optional<UserInfo> findByName(String username);
}
