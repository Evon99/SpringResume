package com.inhatc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeActivity;
import com.inhatc.web.entity.ResumeForeign;
import com.inhatc.web.entity.ResumeUniversity;

public interface ResumeForeignRepository extends JpaRepository<ResumeForeign, Long> {
	
	@Query("SELECT rf FROM ResumeForeign rf WHERE rf.resume.id = :resumeId")
    List<ResumeForeign> findResumeForeignByResumeId(@Param("resumeId") Long resumeId);
	
	ResumeForeign findResumeForeignById(Long resumeId);
	
	@Modifying
	@Query("DELETE FROM ResumeForeign rf WHERE rf.id >= :id AND rf.resume.id = :resumeId")
	void deleteByResumeIdAndIdGreaterThanEqual(@Param("id") Long id, @Param("resumeId") Long resumeId);
}