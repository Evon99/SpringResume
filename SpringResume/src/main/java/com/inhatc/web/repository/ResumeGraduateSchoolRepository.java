package com.inhatc.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.ResumeGraduateSchool;

public interface ResumeGraduateSchoolRepository extends JpaRepository<ResumeGraduateSchool, Long> {
	
	@Query("SELECT rg FROM ResumeGraduateSchool rg WHERE rg.resume.id = :resumeId")
    List<ResumeGraduateSchool> findResumeGraduateSchoolByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeGraduateSchool findResumeGraduateSchoolById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeGraduateSchool rg WHERE rg.id >= :id AND rg.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
	
	Optional<ResumeGraduateSchool> findById(Long id);
}