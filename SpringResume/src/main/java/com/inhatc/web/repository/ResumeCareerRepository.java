package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.ResumeCareer;
import com.inhatc.web.entity.ResumeUniversity;

public interface ResumeCareerRepository extends JpaRepository<ResumeCareer, Long> {
	
	@Query("SELECT rc FROM ResumeCareer rc WHERE rc.resume.id = :resumeId")
    List<ResumeCareer> findResumeCareerByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeCareer findResumeCareerById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeCareer rc WHERE rc.id >= :id AND rc.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}