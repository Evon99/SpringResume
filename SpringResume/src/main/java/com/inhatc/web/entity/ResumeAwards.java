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

import com.inhatc.web.dto.ResumeAwardsFormDto;
import com.inhatc.web.dto.ResumeCombinedFormDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeAwards extends BaseTimeEntity{
	
    @Id
    @Column(name = "awards_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String awardsName; // 수상명
    
    @Column
    private String awardsYear; // 수상 년도

    @Column
	private String awardsMonth; // 수상 월
	
    @Column
	private String awardsDay; // 수상 일자
    
    @Column
    private String awardsAgencyName; // 수여기관명
    
    @Column
    private String awardsHistory; // 수상내역
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeAwardsFormDto resumeAwardsFormDto) {
    		this.awardsName = resumeAwardsFormDto.getAwardsName();
    		this.awardsYear = resumeAwardsFormDto.getAwardsYear();
    		this.awardsMonth = resumeAwardsFormDto.getAwardsMonth();
    		this.awardsDay = resumeAwardsFormDto.getAwardsDay();
    		this.awardsAgencyName = resumeAwardsFormDto.getAwardsAgencyName();
    		this.awardsHistory = resumeAwardsFormDto.getAwardsHistory();
    }
}
