package com.pingme.api;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.domain.post.dto.PostDTO;
import com.pingme.domain.post.service.PostService;
import com.pingme.infrastructure.dto.ResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/retrieveAll")
    public List<PostDTO> retrieveAll(@RequestBody PostDTO request){
        return postService.retrieveAllPost(request);
    }

    @PostMapping("/create")
    public ResponseDTO createPost(@RequestBody PostDTO request){
        return postService.createPost(request);
    }

    @PostMapping("/update")
    public ResponseDTO updatePost(@RequestBody PostDTO request){
        return postService.updatePost(request);
    }

    @PostMapping("/delete")
    public ResponseDTO deletePost(@RequestBody PostDTO request){
        return postService.deletePost(request);
    }
}
