package com.inhatc.web.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.inhatc.web.constant.Role;
import com.inhatc.web.dto.ResumeCombinedFormDto;
import com.inhatc.web.dto.ResumeUniversityFormDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeUniversity extends BaseTimeEntity{
	
    @Id
    @Column(name = "university_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String uniName; // 대학교 이름
    
    @Column
    private String uniTotalGrade; // 대학교 총 학점
    
    @Column
    private String uniMaxGrade; // 대학교 최대 학점
    
    @Column
    private String uniDurationStartYear; // 대학교 기간 시작 년도
	
    @Column
	private String uniDurationStartMonth; // 대학교 기간 시작 월

    @Column
	private String uniDurationEndYear; // 대학교 기간 끝 년도
	
    @Column
	private String uniDurationEndMonth; // 대학교 기간 끝 월
	
    @Column
    private String uniGraduateDivison; // 대학교 졸업 구분
    
    @Column
    private String uniMajor; // 대학교 전공명
    
    @Column
    private String uniDivision; // 대학교 분류
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeUniversityFormDto resumeUniversityFormDto) {
	    	this.uniName = resumeUniversityFormDto.getUniName();
			this.uniTotalGrade = resumeUniversityFormDto.getUniTotalGrade();
			this.uniMaxGrade = resumeUniversityFormDto.getUniMaxGrade();
			this.uniDurationStartYear = resumeUniversityFormDto.getUniDurationStartYear();
			this.uniDurationStartMonth = resumeUniversityFormDto.getUniDurationStartMonth();
			this.uniDurationEndYear = resumeUniversityFormDto.getUniDurationEndYear();
			this.uniDurationEndMonth = resumeUniversityFormDto.getUniDurationEndMonth();
			this.uniGraduateDivison = resumeUniversityFormDto.getUniGraduateDivison();
			this.uniMajor = resumeUniversityFormDto.getUniMajor();
			this.uniDivision = resumeUniversityFormDto.getUniDivision();
    }
}
