package com.pingme.api;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.domain.post.dto.CommentDTO;
import com.pingme.domain.post.dto.LikeDTO;
import com.pingme.domain.post.dto.PostDTO;
import com.pingme.domain.post.service.CommentService;
import com.pingme.domain.post.service.LikeService;
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
    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping("/main/retrieveAll")
    public List<PostDTO> retrieveAll(@RequestBody PostDTO request){
        return postService.retrieveAllPost(request);
    }

    @PostMapping("/main/create")
    public ResponseDTO createPost(@RequestBody PostDTO request){
        return postService.createPost(request);
    }

    @PostMapping("/main/update")
    public ResponseDTO updatePost(@RequestBody PostDTO request){
        return postService.updatePost(request);
    }

    @PostMapping("/main/delete")
    public ResponseDTO deletePost(@RequestBody PostDTO request){
        return postService.deletePost(request);
    }

    @PostMapping("/comment/retrieve")
    public List<CommentDTO> retrieveComment(@RequestBody CommentDTO request){
        return commentService.retrieveComment(request);
    }

    @PostMapping("/comment/create")
    public CommentDTO createComment(@RequestBody CommentDTO request){
        return commentService.createComment(request);
    }

    @PostMapping("/comment/update")
    public ResponseDTO updateComment(@RequestBody CommentDTO request){
        return commentService.updateComment(request);
    }

    @PostMapping("/comment/delete")
    public ResponseDTO deleteComment(@RequestBody CommentDTO request){
        return commentService.deleteComment(request);
    }

    @PostMapping("/like")
    public Long addLike(@RequestBody LikeDTO request){
        return likeService.addLike(request);
    }

    @PostMapping("/unlike")
    public Long cancelLike(@RequestBody LikeDTO request){
        return likeService.cancelLike(request);
    }


}
