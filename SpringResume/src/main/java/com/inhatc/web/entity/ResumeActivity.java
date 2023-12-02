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

import com.inhatc.web.dto.ResumeActivityFormDto;
import com.inhatc.web.dto.ResumeCombinedFormDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeActivity extends BaseTimeEntity{
	
    @Id
    @Column(name = "activity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String activityName; // 활동명
    
    @Column
    private String activityDurationStartYear; // 경험 기간 시작 년도
	
    @Column
	private String activityDurationStartMonth; // 경험 기간 시작 월
	
    @Column
	private String activityDurationStartDay; // 경험 기간 시작 일자

    @Column
	private String activityDurationEndYear; // 경험 기간 끝 년도
	
    @Column
	private String activityDurationEndMonth; // 경험 기간 끝 월
	
    @Column
	private String activityDurationEndDay; // 경험 기간 끝 일자
    
    @Column
    private String activityAgencyName; // 활동 기관명
    
    @Column
    private String activityHistory; // 활동 내용
    
    @ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeActivityFormDto resumeActivityFormDto) {
    		this.activityName = resumeActivityFormDto.getActivityName();
    		this.activityDurationStartYear = resumeActivityFormDto.getActivityDurationStartYear();
    		this.activityDurationStartMonth = resumeActivityFormDto.getActivityDurationStartMonth();
    		this.activityDurationStartDay = resumeActivityFormDto.getActivityDurationStartDay();
    		this.activityDurationEndYear = resumeActivityFormDto.getActivityDurationEndYear();
    		this.activityDurationEndMonth = resumeActivityFormDto.getActivityDurationEndMonth();
    		this.activityDurationEndDay = resumeActivityFormDto.getActivityDurationEndDay();
    		this.activityAgencyName = resumeActivityFormDto.getActivityAgencyName();
    		this.activityHistory = resumeActivityFormDto.getActivityHistory();
    }
}
