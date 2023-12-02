package com.inhatc.web.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.Resume;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeFormDto{
	
//	private ModelMapper modelMapper;
//
//    public ResumeFormDto(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
	
    private Long id; //기본키
    
    private String resumePictureName; // 이력서 증명사진 파일명
    
    private String resumeOriPictureName; // 이력서 증명사진 원본 파일명
    
    private String resumePictureUrl; // 이력서 증명사진 경로
    
    private String resumeName; // 이력서 이름
    
    private String memberName; // 사용자 본명
    
    private String memberBirthYear; // 사용자 생년월일 (연도)
    
    private String memberBirthMonth; // 사용자 생년월일 (월)
    
    private String memberBirthDay; // 사용자 생년월일 (일)
    
    private String memberGender; // 사용자 성별
    
    private String memberPhoneNumber; // 사용자 전화번호
    
    private String memberCellPhoneNumber; // 사용자 휴대폰
    
    private String memberEmail; // 사용자 이메일

    private String memberSns; // 사용자 SNS
    
    private String memberAddress; // 사용자 주소
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public Resume createResume() {
    	return modelMapper.map(this, Resume.class);
    }
    
    public static ResumeFormDto of(Resume resume) {
    	modelMapper.addMappings(new PropertyMap<Resume, ResumeFormDto>() {
    	    protected void configure() {
    	    	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    	    	
    	    	TypeMap<Resume, ResumeFormDto> typeMap = modelMapper.getTypeMap(Resume.class, ResumeFormDto.class);
    	    	
    	    	if (typeMap == null) {
    	    		map().setMemberName(source.getMemberName());
    	    		map().setMemberEmail(source.getMemberEmail());
    	    	}
    	    }
    	});

    	return modelMapper.map(resume, ResumeFormDto.class);
    }
    
	/*
	 * @Builder public ResumeFormDto(Resume resume) { this.id = resume.getId();
	 * this.resumePictureName = resume.getResumePictureName();
	 * this.resumeOriPictureName = resume.getResumeOriPictureName();
	 * this.resumePictureUrl = resume.getResumePictureUrl(); this.resumeName =
	 * resume.getResumeName(); this.memberName = resume.getMemberName();
	 * this.memberBirthYear = resume.getMemberBirthYear(); this.memberBirthMonth =
	 * resume.getMemberBirthMonth(); this.memberBirthDay =
	 * resume.getMemberBirthDay(); this.memberGender = resume.getMemberGender();
	 * this.memberPhoneNumber = resume.getMemberPhoneNumber();
	 * this.memberCellPhoneNumber = resume.getMemberCellPhoneNumber();
	 * this.memberEmail = resume.getMemberEmail(); this.memberSns =
	 * resume.getMemberSns(); this.memberAddress = resume.getMemberAddress(); }
	 */
    
}
