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
@Table(name = "resume_comment")
@Getter
@Setter
public class ResumeComment extends BaseCommentEntity {
	
	@Id
	@Column(name = "resume_comment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "resume_post_id")
	private ResumePost resumePost;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    private MemberDetail memberDetail;
	
	@Transient // DB에 매핑하지 않음
    private String uploaderNickname;
	
	@Transient // DB에 매핑하지 않음
    private String uploaderImg;
	
	public void setUploaderNickname() {
		if(!member.getMemberDetail().getPictureUrl().isEmpty()) {
			this.uploaderNickname = member.getMemberDetail().getNickname();
		} else if (member != null) {
            this.uploaderNickname = member.getName();
        }
	}
	
	public void setUploaderImg() {
		if (!member.getMemberDetail().getPictureUrl().isEmpty()) {
			this.uploaderImg = member.getMemberDetail().getPictureUrl();
		} else if (member != null) {
            this.uploaderImg = member.getPictureUrl();
        }
	}
}
