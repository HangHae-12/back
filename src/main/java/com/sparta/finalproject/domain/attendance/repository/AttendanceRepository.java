package com.sparta.finalproject.domain.attendance.repository;

import com.sparta.finalproject.domain.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByChildIdAndDate(Long childId, LocalDate date);
}
