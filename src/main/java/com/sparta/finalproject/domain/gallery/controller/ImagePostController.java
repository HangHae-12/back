package com.sparta.finalproject.domain.gallery.controller;

import com.sparta.finalproject.domain.gallery.dto.ImagePostRequestDto;
import com.sparta.finalproject.domain.gallery.service.ImagePostService;
import com.sparta.finalproject.global.dto.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImagePostController {

    private final ImagePostService imagePostService;

    @PostMapping("classroom/{classroomId}/gallery")
    public GlobalResponseDto addImagePost(@PathVariable Long classroomId,
                                          @ModelAttribute ImagePostRequestDto imagePostRequestDto) throws IOException {
        return imagePostService.imagePostAdd(classroomId, imagePostRequestDto);
    }

    @GetMapping("classroom/{classroomId}/gallery/{imagePostId}")
    public GlobalResponseDto findImagePost(@PathVariable Long classroomId, @PathVariable Long imagePostId) {
        return imagePostService.imagePostFind(imagePostId);
    }

//    @GetMapping("api/common/classes/{classroomId}/gallery")
//    public ResponseEntity<List<ImagePostResponseDto>> readImagePostList(@PathVariable Long classroomId,
//                                                                        @RequestParam(required = false, defaultValue = "2000-01-01", value = "start") String startDate,
//                                                                        @RequestParam(required = false, defaultValue = "3000-01-01", value = "end") String endDate,
//                                                                        @RequestParam(required = false, defaultValue = "", value = "keyword") String keyword) {
//        return imagePostService.getImagePostListByCriteria(classroomId, startDate, endDate, keyword);
//    }
    @GetMapping("classroom/{classroomId}/gallery")
    public GlobalResponseDto findImagePostPage(@PathVariable Long classroomId,
                                               @RequestParam(required = false, defaultValue = "2000-01-01", value = "startDate") String startDate,
                                               @RequestParam(required = false, defaultValue = "3000-01-01", value = "endDate") String endDate,
                                               @RequestParam(required = false, defaultValue = "", value = "keyword") String keyword,
                                               @RequestParam(required = false, defaultValue = "1", value = "page") int page,
                                               @RequestParam(required = false, defaultValue = "0", value = "isAsc") boolean isAsc) {
        return imagePostService.imagePostPageFind(classroomId,startDate, endDate, keyword, page-1, isAsc);
    }

    @DeleteMapping("classroom/{classroomId}/gallery/{imagePostId}")
    public GlobalResponseDto deleteImagePost(@PathVariable Long classroomId, @PathVariable Long imagePostId) {
        return imagePostService.imagePostDelete(imagePostId);
    }
}
