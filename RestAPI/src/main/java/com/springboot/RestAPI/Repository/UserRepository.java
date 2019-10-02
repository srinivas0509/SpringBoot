package com.springboot.RestAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RestAPI.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//void save(Optional<User> user);

}
