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
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resume_post")
@Getter
@Setter
public class ResumePost extends BasePostEntity{
	
	@Id
	@Column(name = "resume_post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String resumeFileName; // 보이스 목소리 파일명

	@Column(nullable = false)
	private String oriResumeFileName; // 보이스 목소리 원본파일명
	
	@Column(nullable = false)
	private String resumeFileUrl; // 보이스 파일 주소
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    private MemberDetail memberDetail;
	
	@Transient // DB에 매핑하지 않음
    private String uploaderNickname;
	
	@Transient // DB에 매핑하지 않음
    private String uploaderImg;
	
	@Transient // DB에 매핑하지 않음
    private String resumeFileSize;
	
	public void setUploaderNickname() {
		if(member.getMemberDetail() != null) {
			this.uploaderNickname = member.getMemberDetail().getNickname();
		} else if (member != null) {
            this.uploaderNickname = member.getName();
        }
	}
	
	public void setUploaderImg() {
		if(!member.getMemberDetail().getPictureUrl().isBlank()) {
			this.uploaderImg = member.getMemberDetail().getPictureUrl();
		} else if (member != null) {
            this.uploaderImg = member.getPictureUrl();
        }
	}
	
	public void updateResumePost(String resumeFileName, String oriResumeFileName,
			String resumeFileUrl) {
		this.resumeFileName = resumeFileName;
		this.oriResumeFileName = oriResumeFileName;
		this.resumeFileUrl = resumeFileUrl;
	}
}
