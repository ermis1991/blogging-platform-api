package com.ermis1991.blogapi.service;

import com.ermis1991.blogapi.entity.Post;
import com.ermis1991.blogapi.payload.mapper.PostMapper;
import com.ermis1991.blogapi.payload.request.PostRequest;
import com.ermis1991.blogapi.payload.response.PostResponse;
import com.ermis1991.blogapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public ResponseEntity<PostResponse> createPost(PostRequest postRequest) {
        Post post = postMapper.mapPostRequestToPost(postRequest);

        LocalDateTime now = LocalDateTime.now();
        post.setCreatedAt(now);
        post.setUpdatedAt(now);

        Post savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.mapPostToPostResponse(savedPost));
    }

    public ResponseEntity<PostResponse> updatePostById(Long id, PostRequest postRequest) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Post not found with id %d!",id)
                ));
        Post updatedPost = postMapper.mapPostRequestToUpdatedPost(id, postRequest);
        updatedPost.setCreatedAt(existingPost.getCreatedAt());
        if (postRequest.getTitle().equals(existingPost.getTitle()) &&
            postRequest.getContent().equals(existingPost.getContent()) &&
            postRequest.getCategory().equals(existingPost.getCategory()) &&
            postRequest.getTags().equals(existingPost.getTags())) {
            updatedPost.setUpdatedAt(existingPost.getUpdatedAt());
        } else {
            updatedPost.setUpdatedAt(LocalDateTime.now());
        }
        Post savedPost = postRepository.save(updatedPost);

        return ResponseEntity.status(HttpStatus.OK).body(postMapper.mapPostToPostResponse(savedPost));
    }

    public ResponseEntity<Void> deletePostById(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Post not found with id %d!",id)
                ));
        postRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<PostResponse> getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Post not found with id %d!",id)
                ));
        return ResponseEntity.status(HttpStatus.OK).body(postMapper.mapPostToPostResponse(post));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapPostToPostResponse)
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<PostResponse>> getPostsBySearchTerm(String term) {
        List<PostResponse> posts;

        if (term != null && !term.isEmpty()) {
            posts = searchPostsByTerm(term);
        } else {
             posts = getAllPosts();
        }
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    private List<PostResponse> searchPostsByTerm(String term) {
        List<Post> posts = postRepository.searchByTerm(term);
        return posts.stream()
                .map(postMapper::mapPostToPostResponse)
                .collect(Collectors.toList());
    }

}
