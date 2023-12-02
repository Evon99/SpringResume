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
import com.inhatc.web.dto.ResumeCareerFormDto;
import com.inhatc.web.dto.ResumeCombinedFormDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeCareer extends BaseTimeEntity{
	
    @Id
    @Column(name = "career_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String companyName; // 회사명
    
    @Column
    private String companyPosition; // 회사 직급 직책
    
    @Column
    private String companyDepName; // 회사 부서명
    
    @Column
    private String companyJob; // 회사 담당직무
    
    @Column
    private String careerDurationStartYear; // 경력 기간 시작 년도
	
    @Column
   	private String careerDurationStartMonth; // 경력 기간 시작 월

    @Column
   	private String careerDurationEndYear; // 경력 기간 끝 년도
   	
    @Column
   	private String careerDurationEndMonth; // 경력 기간 끝 월
    
    @Column
    private String companyTenureDivision; // 회사 재직여부
    
    @Column
    private String companyJobContent; // 회사 담당업무
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeCareerFormDto resumeCareerFormDto) {
    		this.companyName = resumeCareerFormDto.getCompanyName();
    		this.companyPosition = resumeCareerFormDto.getCompanyPosition();
    		this.companyDepName = resumeCareerFormDto.getCompanyDepName();
    		this.companyJob = resumeCareerFormDto.getCompanyJob();
    		this.careerDurationStartYear = resumeCareerFormDto.getCareerDurationStartYear();
    		this.careerDurationStartMonth = resumeCareerFormDto.getCareerDurationStartMonth();
    		this.careerDurationEndYear = resumeCareerFormDto.getCareerDurationEndYear();
    		this.careerDurationEndMonth = resumeCareerFormDto.getCareerDurationEndMonth();
    		this.companyTenureDivision = resumeCareerFormDto.getCompanyTenureDivision();
    		this.companyJobContent = resumeCareerFormDto.getCompanyJobContent();
    }
    
}
