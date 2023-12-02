package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeActivity;
import com.inhatc.web.entity.ResumePreferent;
import com.inhatc.web.entity.ResumeUniversity;

public interface ResumePreferentRepository extends JpaRepository<ResumePreferent, Long> {
	
	@Query("SELECT rp FROM ResumePreferent rp WHERE rp.resume.id = :resumeId")
    List<ResumePreferent> findResumePreferentByResumeId(@Param("resumeId") Long resumeId);
	
	ResumePreferent findResumePreferentById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumePreferent rp WHERE rp.id >= :id AND rp.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}