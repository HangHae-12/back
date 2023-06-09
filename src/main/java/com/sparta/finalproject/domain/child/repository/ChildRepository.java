package com.sparta.finalproject.domain.child.repository;

import com.sparta.finalproject.domain.attendance.dto.DateAttendanceResponseDto;
import com.sparta.finalproject.domain.child.entity.Child;
import com.sparta.finalproject.global.enumType.AttendanceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> , ChildRepositoryCustom {
    List<Child> findByClassroomIdAndNameContaining(Long classroomId, String name);
    Optional<Child> findByClassroomIdAndId(Long classroomId, Long Id);
    Page<Child> findAllByClassroomId(Long classroomId, Pageable pageable);
    Long countAllByClassroomId(Long ClassroomId);
    List<Child> findAllByUserId(Long Id);


    @Query("select c from Child c where (:classroomId is null or c.classroom.id = :classroomId)")
    List<Child> findAllByClassroomId(Long classroomId);

    @Query("select c from Child c join c.classroom r join r.kindergarten k where k.id = :kindergartenId")
    List<Child> findAllByKindergartenId(Long kindergartenId);

    @Query("select c from Child c join c.attendanceList a join c.classroom r join  r.kindergarten k where k.id = :kindergartenId and a.date = :date and a.status = :status")
    List<Child> findAllByEnteredAndKindergartenId(@Param("date") LocalDate date, @Param("status") AttendanceStatus status, @Param("kindergartenId") Long kindergartenId);

    @Query("select c from Child c join c.attendanceList a join c.classroom r where (:classroomId is null or r.id = :classroomId) and a.date = :date and a.status = :status")
    List<Child> findAllByEnteredAndClassroomId(@Param("date") LocalDate date, @Param("status") AttendanceStatus status, @Param("classroomId") Long classroomId);

    @Query("select new com.sparta.finalproject.domain.attendance.dto.DateAttendanceResponseDto(c.id, c.name, a.status, a.enterTime, a.exitTime, a.absentReason) " + "from Child c join c.attendanceList a " + "where a.date = :date and c.id =:childId")
    DateAttendanceResponseDto findDateAttendance(@Param("date") LocalDate date, @Param("childId") Long childId);
}
