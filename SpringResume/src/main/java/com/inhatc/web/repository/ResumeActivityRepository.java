package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.ResumeActivity;
import com.inhatc.web.entity.ResumeUniversity;

public interface ResumeActivityRepository extends JpaRepository<ResumeActivity, Long> {
	
	@Query("SELECT ra FROM ResumeActivity ra WHERE ra.resume.id = :resumeId")
    List<ResumeActivity> findResumeActivityByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeActivity findResumeActivityById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeActivity ra WHERE ra.id >= :id AND ra.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}