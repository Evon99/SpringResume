package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.ResumeUniversity;

public interface ResumeUniversityRepository extends JpaRepository<ResumeUniversity, Long> {
	
	@Query("SELECT ru FROM ResumeUniversity ru WHERE ru.resume.id = :resumeId")
    List<ResumeUniversity> findResumeUniversityByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeUniversity findResumeUniversityById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeUniversity ru WHERE ru.id >= :id AND ru.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}