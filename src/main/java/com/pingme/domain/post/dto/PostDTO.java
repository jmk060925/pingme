package com.pingme.domain.post.dto;

import java.util.ArrayList;
import java.util.List;

import com.pingme.domain.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long docNo;

    private String username;

    private String txtCntn;

    private String imgCntn;

    private String videoCntn;

    private String tag;

    public static List<PostDTO> fromEntityList(List<Post> postEntityList){
        List<PostDTO>  postList = new ArrayList<PostDTO>();

        for(Post entity : postEntityList){
            postList.add(PostDTO.builder().docNo(entity.getDocNo()).username(entity.getUsername()).txtCntn(entity.getTxtCntn()).imgCntn(entity.getImgCntn()).videoCntn(entity.getVideoCntn()).tag(entity.getTag()).build());
        }
        return postList;
    }

    public static PostDTO fromEntity(Post entity){
        return PostDTO.builder().docNo(entity.getDocNo()).username(entity.getUsername()).txtCntn(entity.getTxtCntn()).imgCntn(entity.getImgCntn()).videoCntn(entity.getVideoCntn()).tag(entity.getTag()).build();
    }

    public Post toEntity(PostDTO postDto){
        return Post.builder().docNo(postDto.getDocNo()).username(postDto.getUsername()).txtCntn(postDto.getTxtCntn()).imgCntn(postDto.getImgCntn()).videoCntn(postDto.getVideoCntn()).tag(postDto.getTag()).build();
    }
}
