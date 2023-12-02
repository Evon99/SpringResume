package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeActivity;
import com.inhatc.web.entity.ResumeLanguage;
import com.inhatc.web.entity.ResumeUniversity;

public interface ResumeLanguageRepository extends JpaRepository<ResumeLanguage, Long> {
	
	@Query("SELECT rl FROM ResumeLanguage rl WHERE rl.resume.id = :resumeId")
    List<ResumeLanguage> findResumeLanguageByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeLanguage findResumeLanguageById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeLanguage rl WHERE rl.id >= :id AND rl.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}