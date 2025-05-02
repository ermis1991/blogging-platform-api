package com.ermis1991.blogapi.controller;

import com.ermis1991.blogapi.payload.request.PostRequest;
import com.ermis1991.blogapi.payload.response.PostResponse;
import com.ermis1991.blogapi.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePostById(@PathVariable Long id,
                                                       @RequestBody @Valid PostRequest postRequest) {
        return postService.updatePostById(id, postRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
        return postService.deletePostById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPostsBySearchTerm(
            @RequestParam(name = "term", required = false) String term) {
        return postService.getPostsBySearchTerm(term);
    }

}
