package com.sparta.finalproject.domain.classroom.entity;

import com.sparta.finalproject.domain.classroom.dto.TeacherRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String name;
    @Column
    private String gender;
    @Column
    private LocalDate birth;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String resolution;
    @Column
    private String profileImageURl;
    @ManyToOne
    private Classroom classroom;

    @Builder
    public Teacher(TeacherRequestDto teacherRequestDto, Classroom classroom){
        this.name = teacherRequestDto.getName();
        this.gender = teacherRequestDto.getGender();
        this.birth =teacherRequestDto.getBirth();
        this.phoneNumber = teacherRequestDto.getPhoneNumber();
        this.email = teacherRequestDto.getEmail();
        this.resolution = teacherRequestDto.getResolution();
        this.profileImageURl = teacherRequestDto.getProfileImageUrl();
        this.classroom = classroom;
    }
    public static Teacher of(TeacherRequestDto teacherRequestDto, Classroom classroom){
        return Teacher.builder()
                .teacherRequestDto(teacherRequestDto)
                .classroom(classroom)
                .build();
    }
}
