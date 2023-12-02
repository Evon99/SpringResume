package com.inhatc.web.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
	
	List<Resume> findAllByMember_IdOrderByRegTimeAsc(Long memberId);
	
	Resume findResumeById(Long resumeId);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM Resume r WHERE r.id = :resumeId")
    void deleteByResumeId(@Param("resumeId") Long resumeId);
}