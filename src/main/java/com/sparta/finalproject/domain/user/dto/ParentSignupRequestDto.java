package com.sparta.finalproject.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ParentSignupRequestDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{1,15}$", message = "빈 공간이 아닌, 특수문자를 제외하고 15자 이내로 입력 해주세요.")
    private String name;

    @NotNull
    @Pattern(regexp = "^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$", message = "빈 공간이 아닌, 01X-XXXX-XXXX 또는 01X-XXX-XXXX 형식으로 휴대폰 전화번호를 입력 해주세요.")
    private String phoneNumber;

    @Pattern(regexp = "^$|(?:(?:01(?:0|1|[6-9])-(?:\\d{3,4})-\\d{4})|(?:02|0[3-6][1-4]|01(?:0|1|[6-9]))-(?:\\d{3,4})-\\d{4})$", message = "휴대폰 전화번호 또는 집 전화번호를 입력 해주세요.")
    private String emergencyPhoneNumber;

    private MultipartFile profileImage;

    private String relationship;
}
