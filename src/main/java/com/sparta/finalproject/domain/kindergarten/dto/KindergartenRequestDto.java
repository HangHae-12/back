package com.sparta.finalproject.domain.kindergarten.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class KindergartenRequestDto {
    private MultipartFile logoImage;
    private String kindergartenName;
    private String contactNumber;
    private List<String> classroomList;
    private String address;
}
