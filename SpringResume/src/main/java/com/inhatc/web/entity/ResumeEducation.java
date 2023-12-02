package com.inhatc.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.inhatc.web.dto.ResumeCombinedFormDto;
import com.inhatc.web.dto.ResumeEducationFormDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeEducation extends BaseTimeEntity{
	
    @Id
    @Column(name = "education_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String eduName; // 교육명
    
    @Column
    private String eduDurationStartYear; // 교육 기간 시작 년도
	
    @Column
	private String eduDurationStartMonth; // 교육 기간 시작 월
	
    @Column
	private String eduDurationStartDay; // 교육 기간 시작 일자

    @Column
	private String eduDurationEndYear; // 교육 기간 끝 년도
	
    @Column
	private String eduDurationEndMonth; // 교육 기간 끝 월

    @Column
	private String eduDurationEndDay; // 교육 기간 끝 일자
    
    @Column
    private String eduAgencyName; // 교육 기관명
    
    @Column
    private String eduHistory; // 교육 내용
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeEducationFormDto resumeEducationFormDto) {
    		this.eduName = resumeEducationFormDto.getEduName();
    		this.eduDurationStartYear = resumeEducationFormDto.getEduDurationStartYear();
    		this.eduDurationStartMonth = resumeEducationFormDto.getEduDurationStartMonth();
    		this.eduDurationStartDay = resumeEducationFormDto.getEduDurationStartDay();
    		this.eduDurationEndYear = resumeEducationFormDto.getEduDurationEndYear();
    		this.eduDurationEndMonth = resumeEducationFormDto.getEduDurationEndMonth();
    		this.eduDurationEndDay = resumeEducationFormDto.getEduDurationEndDay();
    		this.eduAgencyName = resumeEducationFormDto.getEduAgencyName();
    		this.eduHistory = resumeEducationFormDto.getEduHistory();
    }
}
