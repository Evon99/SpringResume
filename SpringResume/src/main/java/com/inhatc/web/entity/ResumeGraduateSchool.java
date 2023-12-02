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
import com.inhatc.web.dto.ResumeGraduateSchoolFormDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeGraduateSchool extends BaseTimeEntity{
	
    @Id
    @Column(name = "gradShcool_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String gradSchoolName; // 대학원 이름
    
    @Column
    private String gradTotalGrade; // 대학원 총 학점
	
    @Column
	private String gradMaxGrade; // 대학원 최대 학점 
    
    @Column
    private String gradDurationStartYear; // 대학원 기간 시작 년도
	
    @Column
	private String gradDurationStartMonth; // 대학원 기간 시작 월

    @Column
	private String gradDurationEndYear; // 대학원 기간 끝 년도
	
    @Column
	private String gradDurationEndMonth; // 대학원 기간 끝 월
    
    @Column
    private String gradSchoolGraduateDivison; // 대학원 졸업 구분
    
    @Column
    private String gradSchoolMajor; // 대학원 전공명
    
    @Column
    private String gradSchoolDivision; // 대학원 분류
    
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @OneToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeGraduateSchoolFormDto resumeGraduateSchoolFormDto) {
	    	this.gradSchoolName = resumeGraduateSchoolFormDto.getGradSchoolName();
			this.gradTotalGrade = resumeGraduateSchoolFormDto.getGradTotalGrade();
			this.gradMaxGrade = resumeGraduateSchoolFormDto.getGradMaxGrade();
			this.gradDurationStartYear = resumeGraduateSchoolFormDto.getGradDurationStartYear();
			this.gradDurationStartMonth = resumeGraduateSchoolFormDto.getGradDurationStartMonth();
			this.gradDurationEndYear = resumeGraduateSchoolFormDto.getGradDurationEndYear();
			this.gradDurationEndMonth = resumeGraduateSchoolFormDto.getGradDurationEndMonth();
			this.gradSchoolGraduateDivison = resumeGraduateSchoolFormDto.getGradSchoolGraduateDivison();
			this.gradSchoolMajor = resumeGraduateSchoolFormDto.getGradSchoolMajor();
			this.gradSchoolDivision = resumeGraduateSchoolFormDto.getGradSchoolDivision();
    }
}
