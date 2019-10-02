package com.springboot.RestAPI.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.RestAPI.Model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser_Id(int userid);

}
