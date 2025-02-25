package com.pingme.domain.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.domain.post.dto.PostDTO;
import com.pingme.domain.post.entity.Post;
import com.pingme.domain.post.repository.PostRepository;
import com.pingme.infrastructure.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    //TODO: Follwer의 Post도 같이 보일 수 있게 조건 처리
    public List<PostDTO> retrieveAllPost(PostDTO request){
        return PostDTO.fromEntityList(postRepository.findAll());
    }

    public ResponseDTO createPost(PostDTO request){

        PostDTO dto = PostDTO.fromEntity(postRepository.save(request.toEntity(request)));

        if(dto == null){
            return ResponseDTO.builder().resultcode("F").msg("Posting is failed.").build();
        }else{
            return ResponseDTO.builder().resultcode("S").msg("").build();
        }

        
    }

    public ResponseDTO updatePost(PostDTO request){

        Post post = postRepository.findById(request).get();

        post.setTxtCntn(request.getTxtCntn());
        post.setImgCntn(request.getImgCntn());
        post.setVideoCntn(request.getVideoCntn());
        post.setTag(request.getTag());


        return ResponseDTO.builder().resultcode("S").msg("").build();
    }

    public ResponseDTO deletePost(PostDTO request){
        postRepository.delete(request.toEntity(request));
        return ResponseDTO.builder().resultcode("S").msg("").build();
    }
}
