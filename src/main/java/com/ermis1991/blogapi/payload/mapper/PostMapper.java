package com.ermis1991.blogapi.payload.mapper;

import com.ermis1991.blogapi.entity.Post;
import com.ermis1991.blogapi.payload.request.PostRequest;
import com.ermis1991.blogapi.payload.response.PostResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class PostMapper {

    public Post mapPostRequestToPost (PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .category(postRequest.getCategory())
                .tags(postRequest.getTags() == null ? new ArrayList<>() : postRequest.getTags())
                .build();
    }

    public PostResponse mapPostToPostResponse (Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .tags(post.getTags() == null ? new ArrayList<>() : post.getTags())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public Post mapPostRequestToUpdatedPost (Long id, PostRequest postRequest) {
        return Post.builder()
                .id(id)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .category(postRequest.getCategory())
                .tags(postRequest.getTags() == null ? new ArrayList<>() : postRequest.getTags())
                .build();
    }

}
