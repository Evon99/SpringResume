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
import com.inhatc.web.dto.ResumePreferentFormDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumePreferent extends BaseTimeEntity{
	
    @Id
    @Column(name = "preferent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String veteransDivison; // 보훈사항
    
    @Column
    private String obstacleDivsion; // 장애여부
    
    @Column
    private String obstacleGrade; // 장애등급
    
    @Column
    private String militaryDivison; // 병역구분
    
    @Column
    private String militaryDurationStartYear; // 복무 기간 시작 년도
	
    @Column
	private String militaryDurationStartMonth; // 복무 기간 시작 월

    @Column
	private String militaryDurationEndYear; // 복무 기간 끝 년도
	
    @Column
	private String militaryDurationEndMonth; // 복무 기간 끝 월
    
    @Column
    private String byMilitaryDuration; // 군별
    
    @Column
    private String MilitaryClasses; // 군 계급
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumePreferentFormDto resumePreferentFormDto) {
    		this.veteransDivison = resumePreferentFormDto.getVeteransDivison();
    		this.obstacleDivsion = resumePreferentFormDto.getObstacleDivsion();
    		this.obstacleGrade = resumePreferentFormDto.getObstacleGrade();
    		this.militaryDivison = resumePreferentFormDto.getMilitaryDivison();
    		this.militaryDurationStartYear = resumePreferentFormDto.getMilitaryDurationStartYear();
    		this.militaryDurationStartMonth = resumePreferentFormDto.getMilitaryDurationStartMonth();
    		this.militaryDurationEndYear = resumePreferentFormDto.getMilitaryDurationEndYear();
    		this.militaryDurationEndMonth = resumePreferentFormDto.getMilitaryDurationEndMonth();
    		this.byMilitaryDuration = resumePreferentFormDto.getByMilitaryDuration();
    		this.MilitaryClasses = resumePreferentFormDto.getMilitaryClasses();
    }
    
}
