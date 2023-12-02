package com.inhatc.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeValueFormDto {

	private String memberBirthYear; // 멤버의 생일 년도

	private String memberBirthMonth; // 멤버의 생일 월
	
	private String memberBirthDay; // 멤버의 생일 일자
	
	private List<String> uniTotalGrade; // 대학교 학점
	 
	private List<String> uniMaxGrade; // 대학교 최대 학점 
	
	private List<String> uniDurationStartYear; // 대학교 기간 시작 년도
	
	private List<String> uniDurationStartMonth; // 대학교 기간 시작 월

	private List<String> uniDurationEndYear; // 대학교 기간 끝 년도
	
	private List<String> uniDurationEndMonth; // 대학교 기간 끝 월
	
	private List<String> gradTotalGrade; // 대학원 학점
	 
	private List<String> gradMaxGrade; // 대학원 최대 학점 
	
	private List<String> gradDurationStartYear; // 대학원 기간 시작 년도
	
	private List<String> gradDurationStartMonth; // 대학원 기간 시작 월

	private List<String> gradDurationEndYear; // 대학원 기간 끝 년도
	
	private List<String> gradDurationEndMonth; // 대학원 기간 끝 월
	
	private List<String> careerDurationStartYear; // 경력 기간 시작 년도
	
	private List<String> careerDurationStartMonth; // 경력 기간 시작 월

	private List<String> careerDurationEndYear; // 경력 기간 끝 년도
	
	private List<String> careerDurationEndMonth; // 경력 기간 끝 월
	
	private List<String> languageTotalScore; // 언어 점수
	 
	private List<String> languageRating; // 언어 급수 
	
	private List<String> languageAcqYear; // 언어 취득 년도

	private List<String> languageAcqMonth; // 언어 취득 월
	
	private List<String> languageAcqDay; // 언어 취득 일자
	
	private List<String> awardsYear; // 수상 년도

	private List<String> awardsMonth; // 수상 월
	
	private List<String> awardsDay; // 수상 일자
	
	private List<String> foreignDurationStartYear; // 해외 기간 시작 년도
	
	private List<String> foreignDurationStartMonth; // 해외 기간 시작 월

	private List<String> foreignDurationEndYear; // 해외 기간 끝 년도
	
	private List<String> foreignDurationEndMonth; // 해외 기간 끝 월
	
	private List<String> activityDurationStartYear; // 경험 기간 시작 년도
	
	private List<String> acitivityDurationStartMonth; // 경험 기간 시작 월
	
	private List<String> acitivityDurationStartDay; // 경험 기간 시작 일자

	private List<String> activityDurationEndYear; // 경험 기간 끝 년도
	
	private List<String> activityDurationEndMonth; // 경험 기간 끝 월
	
	private List<String> activityDurationEndDay; // 경험 기간 끝 일자
	
	private List<String> eduDurationStartYear; // 교육 기간 시작 년도
	
	private List<String> eduDurationStartMonth; // 교육 기간 시작 월
	
	private List<String> eduDurationStartDay; // 교육 기간 시작 일자

	private List<String> eduDurationEndYear; // 교육 기간 끝 년도
	
	private List<String> eduDurationEndMonth; // 교육 기간 끝 월
	
	private List<String> eduDurationEndDay; // 교육 기간 끝 일자
	
	private List<String> veteransDivision; // 보훈 사항
	
	private List<String> obstacleDivison; // 장애여부
	
	private List<String> militaryDivision; // 병역구분
	
	private List<String> militaryDurationStartYear; // 복무 기간 시작 년도
	
	private List<String> militaryDurationStartMonth; // 복무 기간 시작 월

	private List<String> militaryDurationEndYear; // 복무 기간 끝 년도
	
	private List<String> militaryDurationEndMonth; // 복무 기간 끝 월
	
}

