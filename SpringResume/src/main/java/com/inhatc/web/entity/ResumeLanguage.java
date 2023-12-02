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
import com.inhatc.web.dto.ResumeLanguageFormDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeLanguage extends BaseTimeEntity{
	
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String languageName; // 언어명
    
    @Column
    private String languageTotalScore; // 언어 점수
	 
    @Column
	private String languageRating; // 언어 급수 
    
    @Column
    private String languageAcqYear; // 언어 취득 년도

    @Column
	private String languageAcqMonth; // 언어 취득 월
	
    @Column
	private String languageAcqDay; // 언어 취득 일자
    
    @Column
    private String languageTestName; // 언어 시험명
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeLanguageFormDto resumeLanguageFormDto) {
    		this.languageName = resumeLanguageFormDto.getLanguageName();
    		this.languageTotalScore = resumeLanguageFormDto.getLanguageTotalScore();
    		this.languageRating = resumeLanguageFormDto.getLanguageRating();
    		this.languageAcqYear = resumeLanguageFormDto.getLanguageAcqYear();
    		this.languageAcqMonth = resumeLanguageFormDto.getLanguageAcqMonth();
    		this.languageAcqDay = resumeLanguageFormDto.getLanguageAcqDay();
    		this.languageTestName = resumeLanguageFormDto.getLanguageTestName();
    }
}
