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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeHighSchool extends BaseTimeEntity{
	
    @Id
    @Column(name = "highSchool_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column(nullable = false)
    private String highSchoolGraduateYear; // 고등학교 졸업년도
    
    @Column(nullable = false)
    private String highSchoolName; // 고등학교 이름
    
    @Column(nullable = false)
    private String highSchoolDivision; // 고등학교 졸업구분
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeCombinedFormDto resumeCombinedFormDto) {
    	this.highSchoolGraduateYear = resumeCombinedFormDto.getResumeHighSchoolFormDto().getHighSchoolGraduateYear();
		this.highSchoolName = resumeCombinedFormDto.getResumeHighSchoolFormDto().getHighSchoolName();
		this.highSchoolDivision = resumeCombinedFormDto.getResumeHighSchoolFormDto().getHighSchoolDivision();
    }

}
