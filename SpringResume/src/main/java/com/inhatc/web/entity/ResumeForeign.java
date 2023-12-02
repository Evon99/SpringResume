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
import com.inhatc.web.dto.ResumeForeignFormDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResumeForeign extends BaseTimeEntity{
	
    @Id
    @Column(name = "awards_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String countryName; // 국가명
    
    @Column
    private String foreignDurationStartYear; // 해외 기간 시작 년도
	
    @Column
   	private String foreignDurationStartMonth; // 해외 기간 시작 월

    @Column
   	private String foreignDurationEndYear; // 해외 기간 끝 년도
   	
    @Column
   	private String foreignDurationEndMonth; // 해외 기간 끝 월
    
    @Column
    private String foreignAgencyName; // 해외 기관명
    
    @Column
    private String foreignHistory; // 해외 경험 내용
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    
    public void update(ResumeForeignFormDto resumeForeignFormDto) {
    		this.countryName = resumeForeignFormDto.getCountryName();
    		this.foreignDurationStartYear = resumeForeignFormDto.getForeignDurationStartYear();
    		this.foreignDurationStartMonth = resumeForeignFormDto.getForeignDurationStartMonth();
    		this.foreignDurationEndYear = resumeForeignFormDto.getForeignDurationEndYear();
    		this.foreignDurationEndMonth = resumeForeignFormDto.getForeignDurationEndMonth();
    		this.foreignAgencyName = resumeForeignFormDto.getForeignAgencyName();
    		this.foreignHistory = resumeForeignFormDto.getForeignHistory();
    }
}
