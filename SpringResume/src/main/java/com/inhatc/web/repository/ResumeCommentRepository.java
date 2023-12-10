package com.inhatc.web.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeComment;

public interface ResumeCommentRepository extends JpaRepository<ResumeComment, Long> {
	
	Page<ResumeComment> findByResumePost_IdOrderByRegTimeAsc(Long resumePostId, Pageable pageable);
}