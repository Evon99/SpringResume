package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.ResumeAwards;
import com.inhatc.web.entity.ResumeUniversity;

public interface ResumeAwardsRepository extends JpaRepository<ResumeAwards, Long> {
	
	@Query("SELECT ra FROM ResumeAwards ra WHERE ra.resume.id = :resumeId")
    List<ResumeAwards> findResumeAwardsByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeAwards findResumeAwardsById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeAwards ra WHERE ra.id >= :id AND ra.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}