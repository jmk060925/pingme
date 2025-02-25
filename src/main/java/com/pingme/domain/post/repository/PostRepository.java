package com.pingme.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pingme.domain.post.dto.PostDTO;
import com.pingme.domain.post.entity.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post,PostDTO>  {

}
