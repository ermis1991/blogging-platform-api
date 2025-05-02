package com.ermis1991.blogapi.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PostRequest {

    @NotNull(message = "Please enter a title!")
    @Size(min = 2, message = "Title should be at least 2 characters!")
    private String title;

    @NotNull(message = "Please enter a content!")
    @Size(min = 10, message = "Content should be at least 10 characters!")
    private String content;

    @NotNull(message = "Please enter a category!")
    private String category;

    private List<String> tags;

}
