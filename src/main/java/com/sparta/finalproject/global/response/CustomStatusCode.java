package com.sparta.finalproject.global.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomStatusCode {

    //Global
    NOT_VALID_REQUEST(400,  "유효하지 않은 요청입니다."),
    NOT_VALID_TOKEN(400,"유효한 토큰이 아닙니다."),
    TOKEN_NOT_FOUND(400,  "토큰이 없습니다."),

    //Success

    //User 관련
    SIGN_UP_SUCCESS(200, "회원 가입에 성공했습니다."),
    LOG_IN_SUCCESS(200, "로그인되었습니다."),
    ESSENTIAI_INFO_EMPTY(200, "추가적인 정보 입력이 필요합니다."),
    ESSENTIAI_INFO_EXIST(201, "추가적인 정보가 이미 존재합니다."),
    FINAL_SIGNUP_PARENT(200, "회원가입을 축하드립니다."),
    FINAL_SIGNUP_TEACHER(200, "회원가입을 축하드립니다."),
    DIFFRENT_ADMIN_TOKEN(400,"관리자 비밀번호가 틀렸습니다."),
    PROFILE_INFO_GET_SUCCESS(200, "유저 프로필 정보 조회 성공."),
    DIFFRENT_ROLE(400, "권한이 없습니다."),
    PROFILE_INFO_CHANGE_SUCCESS(200, "프로필 정보 수정 완료."),


    //Classroom 관련
    ADD_CLASSROOM_SUCCESS(200, "반이 생성되었습니다."),
    FIND_CLASSROOM_SUCCESS(200,"반이 로드되었습니다."),
    MODIFY_TEACHER_SUCCESS(200,"담임 선생님 정보가 설정되었습니다."),

    //ImagePost 관련
    ADD_IMAGE_POST_SUCCESS(200,"사진 게시글이 등록되었습니다."),
    FIND_IMAGE_LIST_SUCCESS(200,"사진 목록이 로드되었습니다."),
    FIND_IMAGE_POST_PAGE_SUCCESS(200, "사진 게시글 목록이 로드되었습니다."),
    MODIFY_IMAGE_POST_SUCCESS(200, "사진 게시글이 수정되었습니다."),
    DELETE_IMAGE_POST_SUCCESS(200, "사진 게시글이 삭제되었습니다."),


    // child 관련
    ADD_CHILD_SUCCESS(200, "아이 정보가 생성되었습니다."),
    FIND_CHILDREN_SUCCESS(200, "아이들 목록이 로드되었습니다."),
    FIND_CHILD_SUCCESS(200, "아이 정보가 로드되었습니다."),
    MODIFY_CHILD_SUCCESS(200, "아이 정보가 수정되었습니다."),
    SEARCH_CHILD_SUCCESS(200, "검색한 아이 정보가 로드되었습니다."),

    GET_CHILD_PROFILE_SUCCESS(200, "아이 정보가 로드되었습니다."),

    UPDATE_CHILD_ATTENDANCE_TIME_SUCCESS(200, "아이의 등하원 시간이 변경 되었습니다."),

    // parent 관련
    GET_PARENT_SUCCESS(200, "학부모 페이지가 로드되었습니다."),

    UPDATE_PARENT_SUCCESS(200, "학부모 프로필이 수정되었습니다."),

    // attendance 관련
    CHILD_ENTER_SUCCESS(200, "등원 처리가 완료되었습니다."),
    CHILD_ENTER_CANCEL(200,"등원 처리가 취소되었습니다."),
    CHILD_EXIT_SUCCESS(200, "하원 처리가 완료되었습니다."),
    CHILD_EXIT_CANCEL(200, "하원 처리가 취소되었습니다."),
    CHILD_ABSENT_SUCCESS(200, "결석 처리가 완료되었습니다."),
    CHILD_ABSENT_CANCEL(200, "결석 처리가 취소되었습니다."),


    // absent 관련
    CREATE_ABSENT_SUCCESS(200, "결석 신청이 완료되었습니다. "),
    DELETE_ABSENT_SUCCESS(200, "결석 신청이 취소되었습니다. "),

    // Schedule 관련
    LOAD_MANAGER_PAGE_SUCCESS(200, "관리자 페이지가 로드되었습니다."),
    FIND_SCHEDULE_SUCCESS(200, "등하원 기록 조회에 성공했습니다."),

    // exception
    CLASSROOM_NOT_FOUND(400, "반을 찾을 수 없습니다."),
    IMAGE_POST_NOT_FOUND(400, "사진 게시글을 찾을 수 없습니다."),
    CHILD_NOT_FOUND(400, "아이를 찾을 수 없습니다."),
    SET_TEACHER_INFO_FAIL(400, "선생님 정보가 설정되지 않았습니다."),
    FilE_CONVERT_FAIL(400, "파일 전환에 실패했습니다."),
    FILE_DELETE_FAIL(400, "파일 삭제에 실패했습니다."),
    PARENT_NOT_FOUND(400, "학부모를 찾을 수 없습니다."),
    IMAGE_UPLOAD_FAIL(400, "이미지 업로드에 실패했습니다."),
    NOT_FOUND_ATTENDANCE(400, "출결 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_USER(400, "인가되지 않은 사용자입니다.");

    // attendance 관련


    private final int statusCode;
    private final String message;
}
