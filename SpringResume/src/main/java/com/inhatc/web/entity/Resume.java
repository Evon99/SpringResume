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
public class Resume extends BaseTimeEntity{
	
    @Id
    @Column(name = "resume_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    
    @Column
    private String resumePictureName; // 이력서 증명사진 파일명
    
    @Column
    private String resumeOriPictureName; // 이력서 증명사진 원본 파일명
    
    @Column
    private String resumePictureUrl; // 이력서 증명사진 경로
    
    @Column(nullable = false)
    private String resumeName; // 이력서 이름
    
    @Column
    private String memberName; // 사용자 본명
    
    @Column
    private String memberBirthYear; // 사용자 생년월일 (연도)
    
    @Column
    private String memberBirthMonth; // 사용자 생년월일 (월)
    
    @Column
    private String memberBirthDay; // 사용자 생년월일 (일)
    
    @Column
    private String memberGender; // 사용자 성별
    
    @Column
    private String memberPhoneNumber; // 사용자 전화번호
    
    @Column
    private String memberCellPhoneNumber; // 사용자 휴대폰
    
    @Column
    private String memberEmail; // 사용자 이메일

    @Column
    private String memberSns; // 사용자 SNS
    
    @Column
    private String memberAddress; // 사용자 주소
    
    @ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
    
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private MemberDetail memberDetail;
    
//    public void updateMemberImg(String pictureName, String oriPictureName, String pictureUrl) {
//    	this.pictureName = pictureName;
//    	this.oriPictureName = oriPictureName;
//    	this.pictureUrl = pictureUrl;
//    }
	
    public MemberDetail getMemberDetail() {
        return memberDetail;
    }
    
    public void update(ResumeCombinedFormDto resumeCombinedFormDto) {
    	this.resumeName = resumeCombinedFormDto.getResumeFormDto().getResumeName();
    	this.memberName = resumeCombinedFormDto.getResumeFormDto().getMemberName();
    	this.memberBirthYear = resumeCombinedFormDto.getResumeFormDto().getMemberBirthYear();
    	this.memberBirthMonth = resumeCombinedFormDto.getResumeFormDto().getMemberBirthMonth();
    	this.memberBirthDay = resumeCombinedFormDto.getResumeFormDto().getMemberBirthDay();
    	this.memberGender = resumeCombinedFormDto.getResumeFormDto().getMemberGender();
    	this.memberPhoneNumber = resumeCombinedFormDto.getResumeFormDto().getMemberPhoneNumber();
    	this.memberCellPhoneNumber = resumeCombinedFormDto.getResumeFormDto().getMemberCellPhoneNumber();
    	this.memberEmail = resumeCombinedFormDto.getResumeFormDto().getMemberEmail();
    	this.memberSns = resumeCombinedFormDto.getResumeFormDto().getMemberSns();
    	this.memberAddress = resumeCombinedFormDto.getResumeFormDto().getMemberAddress();
    }
    
    public void updateResumeImg(String resumePictureName, String resumeOriPictureName, String resumePictureUrl) {
    	this.resumePictureName = resumePictureName;
    	this.resumeOriPictureName = resumeOriPictureName;
    	this.resumePictureUrl = resumePictureUrl;
    }
}
