package com.sparta.finalproject.domain.gallery.controller;

import com.sparta.finalproject.domain.gallery.dto.ImagePostRequestDto;
import com.sparta.finalproject.domain.gallery.dto.ImagePostResponseDto;
import com.sparta.finalproject.domain.gallery.service.ImagePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImagePostController {

    private final ImagePostService imagePostService;

    @PostMapping("api/managers/{classroom_id}/image-posts")
    public ResponseEntity<ImagePostResponseDto> createImagePost(@PathVariable Long classroom_id,
                                                                @RequestPart(value = "data") ImagePostRequestDto imagePostRequestDto,
                                                                @RequestPart(value = "file") List<MultipartFile> multipartFilelist) throws IOException {
        return imagePostService.createImagePost(classroom_id, imagePostRequestDto, multipartFilelist);
    }

    @GetMapping("api/common/image-posts/{image_post_id}")
    public ResponseEntity<ImagePostResponseDto> readImagePost(@PathVariable Long image_post_id){
        return imagePostService.getImagePost(image_post_id);
    }

    @GetMapping("api/common/classes/{classroomId}/gallery")
    public ResponseEntity<List<ImagePostResponseDto>> readImagePostList (@PathVariable Long classroomId,
                                                                        @RequestParam(required = false, defaultValue = "2000-01-01", value = "start") String startDate,
                                                                        @RequestParam(required = false, defaultValue = "3000-01-01", value = "end") String endDate){
        return imagePostService.getImagePostsByPeriod(classroomId, startDate, endDate);
    }
}
