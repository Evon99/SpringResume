package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.ResumeEducation;
import com.inhatc.web.entity.ResumeUniversity;

public interface ResumeEducationRepository extends JpaRepository<ResumeEducation, Long> {
	
	@Query("SELECT re FROM ResumeEducation re WHERE re.resume.id = :resumeId")
    List<ResumeEducation> findResumeEducationByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeEducation findResumeEducationById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeEducation re WHERE re.id >= :id AND re.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}