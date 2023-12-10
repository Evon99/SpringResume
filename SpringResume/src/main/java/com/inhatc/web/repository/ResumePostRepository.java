package com.inhatc.web.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.ResumePost;

public interface ResumePostRepository extends JpaRepository<ResumePost, Long> {
    
	@Query("SELECT rp FROM ResumePost rp ORDER BY rp.regTime DESC, rp.id ASC")
    Page<ResumePost> findAllByOrderByRegTimeDescAndIdAsc(Pageable pageable);
	
	@Query("SELECT rp FROM ResumePost rp WHERE LOWER(rp.title) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY rp.regTime DESC, rp.id ASC")
	Page<ResumePost> findAllByTitleOrderByRegTimeDescAndIdAsc(@Param("title") String title, Pageable pageable);

	@Query("SELECT rp FROM ResumePost rp " +
		       "JOIN rp.memberDetail md " +
		       "WHERE md.nickname LIKE %:nickname% " +
		       "ORDER BY rp.regTime DESC")
	Page<ResumePost> findAllByNicknameContainingOrderByRegTimeDesc(@Param("nickname") String nickname, Pageable pageable);

	
	ResumePost findById(long id);
	
	Page<ResumePost> findByTitleContaining(String title, Pageable pageable);
	
	@Query("SELECT rp.oriResumeFileName FROM ResumePost rp WHERE rp.resumeFileName = :resumeFileName")
    String findOriResumeFileNameByResumeFileName(@Param("resumeFileName") String resumeFileName);
}