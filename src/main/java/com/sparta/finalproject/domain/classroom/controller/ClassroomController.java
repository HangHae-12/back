package com.sparta.finalproject.domain.classroom.controller;

import com.sparta.finalproject.domain.classroom.service.ClassroomService;
import com.sparta.finalproject.domain.security.UserDetailsImpl;
import com.sparta.finalproject.global.dto.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    // 반 생성
    @PostMapping("kindergarten/{kindergartenId}/classroom")
    public GlobalResponseDto classroomAdd(@PathVariable Long kindergartenId, @RequestParam String name,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        return classroomService.addClassroom(kindergartenId, name, userDetails.getUser());
    }

    // 반 수정
    @PutMapping("kindergarten/{kindergartenId}/classroom/{classroomId}")
    public GlobalResponseDto classroomModify(@PathVariable Long kindergartenId, @PathVariable Long classroomId,
                                             @RequestParam String name, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return classroomService.modifyClassroom(kindergartenId, classroomId, name, userDetails.getUser());
    }

    // 반 삭제
    @DeleteMapping("kindergarten/{kindergartenId}/classroom/{classroomId}")
    public GlobalResponseDto classroomDelete(@PathVariable Long kindergartenId, @PathVariable Long classroomId){
        return classroomService.deleteClassroom(kindergartenId, classroomId);
    }

    // 반 리스트 조회
    @GetMapping("kindergarten/{kindergartenId}")
    public GlobalResponseDto classroomListFind(@PathVariable Long kindergartenId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return classroomService.findClassroomList(kindergartenId, userDetails.getUser());
    }

    // 반 별 페이지 조회
    @GetMapping("kindergarten/{kindergartenId}/classroom/{classroomId}")
    public GlobalResponseDto classroomFind(@PathVariable Long kindergartenId, @PathVariable Long classroomId,
                                           @RequestParam(required = false, defaultValue = "1") int page,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return classroomService.findClassroom(kindergartenId, classroomId, page-1, userDetails.getUser());
    }

    // 담임 선생님 설정
    @PutMapping("kindergarten/{kindergartenId}/classroom/{classroomId}/classroom_teacher/{teacherId}")
    public GlobalResponseDto classroomTeacherModify(@PathVariable Long kindergartenId,@PathVariable Long classroomId,
                                                    @PathVariable Long teacherId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return classroomService.modifyClassroomTeacher(kindergartenId, classroomId, teacherId, userDetails.getUser());
    }


}
