package com.inhatc.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.web.entity.ResumeHighSchool;

public interface ResumeHighSchoolRepository extends JpaRepository<ResumeHighSchool, Long> {
	
	ResumeHighSchool findResumeHighSchoolById(Long id);
	
	ResumeHighSchool findResumeHighSchoolByResumeId(Long resumeId);
}