package com.sparta.finalproject.domain.parent.controller;


import com.sparta.finalproject.domain.parent.dto.ParentProfileModifyRequestDto;
import com.sparta.finalproject.domain.parent.service.ParentService;
import com.sparta.finalproject.global.dto.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ParentController {

    private final ParentService parentService;

    // 학부모 프로필 조회
    // 일단은 param으로 받아왔습니다. 추후에 userDetailImpl로 바꿔야 합니다.

    @GetMapping("parent/child/{childId}")
    public GlobalResponseDto parentProfileFind(@RequestParam Long parentId, @PathVariable Long childId){
        return parentService.findParentProfile(parentId, childId);
    }

    // 학부모 프로필 수정
    // 일단은 param으로 받아왔습니다. 추후에 userDetailImpl로 바꿔야 합니다.
    @PatchMapping("parent/child/{childId}/profile")
    public GlobalResponseDto parentProfileModify(@RequestParam Long parentId, @PathVariable Long childId,
                                                 @ModelAttribute ParentProfileModifyRequestDto requestDto) throws IOException {
        return parentService.modifyProfile(requestDto, parentId, childId);
    }

}
