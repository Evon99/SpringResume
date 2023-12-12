package com.inhatc.web.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.inhatc.web.dto.ResumeActivityFormDto;
import com.inhatc.web.dto.ResumeAwardsFormDto;
import com.inhatc.web.dto.ResumeCareerFormDto;
import com.inhatc.web.dto.ResumeCombinedFormDto;
import com.inhatc.web.dto.ResumeEducationFormDto;
import com.inhatc.web.dto.ResumeForeignFormDto;
import com.inhatc.web.dto.ResumeFormDto;
import com.inhatc.web.dto.ResumeGraduateSchoolFormDto;
import com.inhatc.web.dto.ResumeHighSchoolFormDto;
import com.inhatc.web.dto.ResumeLanguageFormDto;
import com.inhatc.web.dto.ResumePreferentFormDto;
import com.inhatc.web.dto.ResumeUniversityFormDto;
import com.inhatc.web.dto.SessionUser;
import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeActivity;
import com.inhatc.web.entity.ResumeAwards;
import com.inhatc.web.entity.ResumeCareer;
import com.inhatc.web.entity.ResumeEducation;
import com.inhatc.web.entity.ResumeForeign;
import com.inhatc.web.entity.ResumeGraduateSchool;
import com.inhatc.web.entity.ResumeHighSchool;
import com.inhatc.web.entity.ResumeLanguage;
import com.inhatc.web.entity.ResumePreferent;
import com.inhatc.web.entity.ResumeUniversity;
import com.inhatc.web.repository.MemberRepository;
import com.inhatc.web.repository.ResumeActivityRepository;
import com.inhatc.web.repository.ResumeAwardsRepository;
import com.inhatc.web.repository.ResumeCareerRepository;
import com.inhatc.web.repository.ResumeEducationRepository;
import com.inhatc.web.repository.ResumeForeignRepository;
import com.inhatc.web.repository.ResumeGraduateSchoolRepository;
import com.inhatc.web.repository.ResumeHighSchoolRepository;
import com.inhatc.web.repository.ResumeLanguageRepository;
import com.inhatc.web.repository.ResumePreferentRepository;
import com.inhatc.web.repository.ResumeRepository;
import com.inhatc.web.repository.ResumeUniversityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeService {

	@Value("${resumeImgLocation}")
	private String resumeImgLocation;
	
	private final FileService fileService;
	
	private final MemberRepository memberRepository;
	
	private final ResumeRepository resumeRepository;
	
	private final ResumeHighSchoolRepository resumeHighSchoolRepository;
	
	private final ResumeUniversityRepository resumeUniversityRepository;
	
	private final ResumeGraduateSchoolRepository resumeGraduateSchoolRepository;
	
	private final ResumeCareerRepository resumeCareerRepository;
	
	private final ResumeLanguageRepository resumeLanguageRepository;
	
	private final ResumeAwardsRepository resumeAwardsRepository;
	
	private final ResumeForeignRepository resumeForeignRepository;
	
	private final ResumeActivityRepository resumeActivityRepository;
	
	private final ResumeEducationRepository resumeEducationRepository;
	
	private final ResumePreferentRepository resumePreferentRepository;
	
	public ResumeCombinedFormDto createCombinedFormDto(Long resumeId) {
		
		 ResumeCombinedFormDto resumeCombinedFormDto = new ResumeCombinedFormDto();
		 if(Objects.equals(resumeId, 0L)) {
			 System.out.println("resumeId:" + resumeId);
			 resumeCombinedFormDto.setResumeUniversityFormDto(Collections.singletonList(new ResumeUniversityFormDto()));
	         resumeCombinedFormDto.setResumeGraduateSchoolFormDto(Collections.singletonList(new ResumeGraduateSchoolFormDto()));
	         resumeCombinedFormDto.setResumeCareerFormDto(Collections.singletonList(new ResumeCareerFormDto()));
	         resumeCombinedFormDto.setResumeLanguageFormDto(Collections.singletonList(new ResumeLanguageFormDto()));
	         resumeCombinedFormDto.setResumeAwardsFormDto(Collections.singletonList(new ResumeAwardsFormDto()));
	         resumeCombinedFormDto.setResumeForeignFormDto(Collections.singletonList(new ResumeForeignFormDto()));
	         resumeCombinedFormDto.setResumeActivityFormDto(Collections.singletonList(new ResumeActivityFormDto()));
	         resumeCombinedFormDto.setResumeEducationFormDto(Collections.singletonList(new ResumeEducationFormDto()));
	         resumeCombinedFormDto.setResumePreferentFormDto(Collections.singletonList(new ResumePreferentFormDto()));
	         return resumeCombinedFormDto;
		 }
		 
		 System.out.println("고등학교:" + resumeHighSchoolRepository.findResumeHighSchoolByResumeId(resumeId));
		 resumeCombinedFormDto.setResumeFormDto(ResumeFormDto.of(resumeRepository.findResumeById(resumeId)));
		 resumeCombinedFormDto.setResumeHighSchoolFormDto(ResumeHighSchoolFormDto.of(resumeHighSchoolRepository.findResumeHighSchoolByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeUniversityFormDto(ResumeUniversityFormDto.listOf(resumeUniversityRepository.findResumeUniversityByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeGraduateSchoolFormDto(ResumeGraduateSchoolFormDto.listOf(resumeGraduateSchoolRepository.findResumeGraduateSchoolByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeCareerFormDto(ResumeCareerFormDto.listOf(resumeCareerRepository.findResumeCareerByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeLanguageFormDto(ResumeLanguageFormDto.listOf(resumeLanguageRepository.findResumeLanguageByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeAwardsFormDto(ResumeAwardsFormDto.listOf(resumeAwardsRepository.findResumeAwardsByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeForeignFormDto(ResumeForeignFormDto.listOf(resumeForeignRepository.findResumeForeignByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeActivityFormDto(ResumeActivityFormDto.listOf(resumeActivityRepository.findResumeActivityByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumeEducationFormDto(ResumeEducationFormDto.listOf(resumeEducationRepository.findResumeEducationByResumeId(resumeId)));
		 resumeCombinedFormDto.setResumePreferentFormDto(ResumePreferentFormDto.listOf(resumePreferentRepository.findResumePreferentByResumeId(resumeId)));
		 return resumeCombinedFormDto;
	}
	
	public void createResume(ResumeCombinedFormDto resumeCombinedFormDto, MultipartFile resumeImgFile, SessionUser user) throws IOException, Exception {
		
		Member member = memberRepository.findMemberByLoginId(user.getLoginId());
		
		String oriPictureName = resumeImgFile.getOriginalFilename();
        String pictureName = "";
        String pictureUrl = "";
        
      //파일 업로드
        if(!StringUtils.isEmpty(oriPictureName)){
            pictureName = fileService.uploadFile(resumeImgLocation, oriPictureName, resumeImgFile.getBytes());
            pictureUrl = "/images/resume/" + pictureName; // 파일 저장 장소
        }
        
		// 이력서 사용자 정보 저장
		Resume resume = resumeCombinedFormDto.getResumeFormDto().createResume();
		resume.setMember(member);
		resume.setResumeOriPictureName(oriPictureName);
		resume.setResumePictureName(pictureName);
		resume.setResumePictureUrl(pictureUrl);
		resumeRepository.save(resume);
		
		// 이력서 고등학교 정보 저장
		ResumeHighSchool resumeHighSchool = resumeCombinedFormDto.getResumeHighSchoolFormDto().createResumeHighSchool();
		resumeHighSchool.setMember(member);
		resumeHighSchool.setResume(resume);
		resumeHighSchoolRepository.save(resumeHighSchool);
		
		// 이력서 대학교 정보 저장
		for(ResumeUniversityFormDto resumeUniversityFormDto : resumeCombinedFormDto.getResumeUniversityFormDto()) {
			ResumeUniversity resumeUniversity = resumeUniversityFormDto.createResumeUniversity();
			resumeUniversity.setMember(member);
			resumeUniversity.setResume(resume);
			resumeUniversityRepository.save(resumeUniversity);
		}
		
		// 이력서 대학원 정보 저장
		for(ResumeGraduateSchoolFormDto resumeGraduateSchoolFormDto : resumeCombinedFormDto.getResumeGraduateSchoolFormDto()) {
			ResumeGraduateSchool resumeGraduateSchool = resumeGraduateSchoolFormDto.createResumeGraduateSchool();
			resumeGraduateSchool.setMember(member);
			resumeGraduateSchool.setResume(resume);
			resumeGraduateSchoolRepository.save(resumeGraduateSchool);
		}
				
		// 이력서 경력 정보 저장
		for(ResumeCareerFormDto resumeCareerFormDto : resumeCombinedFormDto.getResumeCareerFormDto()) {
			ResumeCareer resumeCareer = resumeCareerFormDto.createResumeCareer();
			resumeCareer.setMember(member);
			resumeCareer.setResume(resume);
//			resumeCareer.setCompanyJobContent(resumeCareer.getCompanyJobContent().replace("\r\n","<br/>"));
			resumeCareerRepository.save(resumeCareer);
		}
		
		// 이력서 어학 정보 저장
		for(ResumeLanguageFormDto resumeLanguageFormDto : resumeCombinedFormDto.getResumeLanguageFormDto()) {
			ResumeLanguage resumeLanguage = resumeLanguageFormDto.createResumeLanguage();
			resumeLanguage.setMember(member);
			resumeLanguage.setResume(resume);
			resumeLanguageRepository.save(resumeLanguage);
		}

		// 이력서 수상 정보 저장
		for(ResumeAwardsFormDto resumeAwardsFormDto : resumeCombinedFormDto.getResumeAwardsFormDto()) {
			ResumeAwards resumeAwards = resumeAwardsFormDto.createResumeAwards();
			resumeAwards.setMember(member);
			resumeAwards.setResume(resume);
			resumeAwardsRepository.save(resumeAwards);
		}
		

		// 이력서 해외경험 정보 저장
		for(ResumeForeignFormDto resumeForeignFormDto : resumeCombinedFormDto.getResumeForeignFormDto()) {
			ResumeForeign resumeForeign = resumeForeignFormDto.createResumeForeign();
			resumeForeign.setMember(member);
			resumeForeign.setResume(resume);
			resumeForeignRepository.save(resumeForeign);
		}

		// 이력서 활동&경험 정보 저장
		for(ResumeActivityFormDto resumeActivityFormDto : resumeCombinedFormDto.getResumeActivityFormDto()) {
			ResumeActivity resumeActivity = resumeActivityFormDto.createResumeActivity();
			resumeActivity.setMember(member);
			resumeActivity.setResume(resume);
			resumeActivityRepository.save(resumeActivity);
		}
		
		// 이력서 교육 정보 저장
		for(ResumeEducationFormDto resumeEducationFormDto : resumeCombinedFormDto.getResumeEducationFormDto()) {
			ResumeEducation resumeEducation = resumeEducationFormDto.createResumeEducation();
			resumeEducation.setMember(member);
			resumeEducation.setResume(resume);
			resumeEducationRepository.save(resumeEducation);
		}

		// 이력서 우대사항 정보 저장
		for(ResumePreferentFormDto resumePreferentFormDto : resumeCombinedFormDto.getResumePreferentFormDto()) {
			ResumePreferent resumePreferent = resumePreferentFormDto.createResumePreferent();
			resumePreferent.setMember(member);
			resumePreferent.setResume(resume);
			resumePreferentRepository.save(resumePreferent);
		}
	}
	
	public List<Resume> getResumeList(Long memberId) {
		
		return resumeRepository.findAllByMember_IdOrderByRegTimeAsc(memberId);
	}
	
	public void updateResume(ResumeCombinedFormDto resumeCombinedFormDto, MultipartFile resumeImgFile, SessionUser user) throws IOException, Exception {
		
		Member member = memberRepository.findMemberByLoginId(user.getLoginId());
		
		Resume resume = resumeRepository.findResumeById(resumeCombinedFormDto.getResumeFormDto().getId());
		
		long resumeId = resume.getId();
		
		String resumeOriPictureName = resumeImgFile.getOriginalFilename();
        String resumePictureName = "";
        String resumePictureUrl = "";
        
      //파일 업로드
        if(!StringUtils.isEmpty(resumeOriPictureName)){
            resumePictureName = fileService.uploadFile(resumeImgLocation, resumeOriPictureName, resumeImgFile.getBytes());
            resumePictureUrl = "/images/resume/" + resumePictureName; // 파일 저장 장소
        }
        
        resume.updateResumeImg(resumePictureName, resumeOriPictureName, resumePictureUrl);
		resume.update(resumeCombinedFormDto);
		
		System.out.println("고등학교 수정 시작");
		ResumeHighSchool resumeHighSchool = resumeHighSchoolRepository.findResumeHighSchoolById(resumeCombinedFormDto.getResumeHighSchoolFormDto().getId());
		resumeHighSchool.update(resumeCombinedFormDto);
	
		
		// 대학교 정보 수정
		List<ResumeUniversityFormDto> resumeUniversityFormDtoList = resumeCombinedFormDto.getResumeUniversityFormDto();
		
		for(ResumeUniversityFormDto resumeUniversityFormDto : resumeUniversityFormDtoList) {
			if(resumeUniversityFormDto.getId() == null) {
				ResumeUniversity resumeUniversity = resumeUniversityFormDto.createResumeUniversity();
				resumeUniversity.setMember(member);
				resumeUniversity.setResume(resume);
				resumeUniversityRepository.save(resumeUniversity);
				return;
			}
			
			ResumeUniversity resumeUniversity = resumeUniversityRepository.findResumeUniversityById(resumeUniversityFormDto.getId());
			resumeUniversity.update(resumeUniversityFormDto);
		}
		
		ResumeUniversityFormDto lastResumeUniversityFormDto = resumeUniversityFormDtoList.get(resumeUniversityFormDtoList.size() - 1);
		resumeUniversityRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeUniversityFormDto.getId() + 1, resumeId);

		// 대학원 정보 수정
		List<ResumeGraduateSchoolFormDto> resumeGraduateSchoolFormDtoList = resumeCombinedFormDto.getResumeGraduateSchoolFormDto();
		
		for(ResumeGraduateSchoolFormDto resumeGraduateSchoolFormDto : resumeGraduateSchoolFormDtoList) {
			System.out.println("대학원 이름:" + resumeGraduateSchoolFormDto.getGradSchoolName());
			if(resumeGraduateSchoolFormDto.getId() == null) {
				ResumeGraduateSchool resumeGraduateSchool = resumeGraduateSchoolFormDto.createResumeGraduateSchool();
				resumeGraduateSchool.setMember(member);
				resumeGraduateSchool.setResume(resume);
				resumeGraduateSchoolRepository.save(resumeGraduateSchool);
				return;
			}
			
			ResumeGraduateSchool resumeGraduateSchool = resumeGraduateSchoolRepository.findResumeGraduateSchoolById(resumeGraduateSchoolFormDto.getId());
			resumeGraduateSchool.update(resumeGraduateSchoolFormDto);
		}
		
		ResumeGraduateSchoolFormDto lastResumeGraduateSchoolFormDto = resumeGraduateSchoolFormDtoList.get(resumeGraduateSchoolFormDtoList.size() - 1);
		resumeGraduateSchoolRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeGraduateSchoolFormDto.getId() + 1, resumeId);

		// 경력 정보 수정
		List<ResumeCareerFormDto> resumeCareerFormDtoList = resumeCombinedFormDto.getResumeCareerFormDto();
				
		for(ResumeCareerFormDto resumeCareerFormDto : resumeCareerFormDtoList) {
			if(resumeCareerFormDto.getId() == null) {
				ResumeCareer resumeCareer = resumeCareerFormDto.createResumeCareer();
				resumeCareer.setMember(member);
				resumeCareer.setResume(resume);
				resumeCareerRepository.save(resumeCareer);
				return;
			}
			
			ResumeCareer resumeCareer = resumeCareerRepository.findResumeCareerById(resumeCareerFormDto.getId());
			resumeCareer.update(resumeCareerFormDto);
		}
		
		ResumeCareerFormDto lastResumeCareerFormDto = resumeCareerFormDtoList.get(resumeCareerFormDtoList.size() - 1);
		resumeCareerRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeCareerFormDto.getId() + 1, resumeId);

		// 어학 정보 수정
        List<ResumeLanguageFormDto> resumeLanguageFormDtoList = resumeCombinedFormDto.getResumeLanguageFormDto();
				
		for(ResumeLanguageFormDto resumeLanguageFormDto : resumeLanguageFormDtoList) {
			if(resumeLanguageFormDto.getId() == null) {
				ResumeLanguage resumeLanguage = resumeLanguageFormDto.createResumeLanguage();
				resumeLanguage.setMember(member);
				resumeLanguage.setResume(resume);
				resumeLanguageRepository.save(resumeLanguage);
				return;
			}
			
			ResumeLanguage resumeLanguage = resumeLanguageRepository.findResumeLanguageById(resumeLanguageFormDto.getId());
			resumeLanguage.update(resumeLanguageFormDto);
		}
		
		ResumeLanguageFormDto lastResumeLanguageFormDto = resumeLanguageFormDtoList.get(resumeLanguageFormDtoList.size() - 1);
		resumeLanguageRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeLanguageFormDto.getId() + 1, resumeId);

		// 수상 정보 수정
        List<ResumeAwardsFormDto> resumeAwardsFormDtoList = resumeCombinedFormDto.getResumeAwardsFormDto();
		
		for(ResumeAwardsFormDto resumeAwardsFormDto : resumeAwardsFormDtoList) {
			if(resumeAwardsFormDto.getId() == null) {
				ResumeAwards resumeAwards = resumeAwardsFormDto.createResumeAwards();
				resumeAwards.setMember(member);
				resumeAwards.setResume(resume);
				resumeAwardsRepository.save(resumeAwards);
				return;
			}
			
			ResumeAwards resumeAwards = resumeAwardsRepository.findResumeAwardsById(resumeAwardsFormDto.getId());
			resumeAwards.update(resumeAwardsFormDto);
		}
		
		ResumeAwardsFormDto lastResumeAwardsFormDto = resumeAwardsFormDtoList.get(resumeAwardsFormDtoList.size() - 1);
		resumeAwardsRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeAwardsFormDto.getId() + 1, resumeId);

		// 해외연수 정보 수정
        List<ResumeForeignFormDto> resumeForeignFormDtoList = resumeCombinedFormDto.getResumeForeignFormDto();
		
		for(ResumeForeignFormDto resumeForeignFormDto : resumeForeignFormDtoList) {
			if(resumeForeignFormDto.getId() == null) {
				ResumeForeign resumeForeign = resumeForeignFormDto.createResumeForeign();
				resumeForeign.setMember(member);
				resumeForeign.setResume(resume);
				resumeForeignRepository.save(resumeForeign);
				return;
			}
			
			ResumeForeign resumeForeign = resumeForeignRepository.findResumeForeignById(resumeForeignFormDto.getId());
			resumeForeign.update(resumeForeignFormDto);
		}
		
		ResumeForeignFormDto lastResumeForeignFormDto = resumeForeignFormDtoList.get(resumeForeignFormDtoList.size() - 1);
		resumeForeignRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeForeignFormDto.getId() + 1, resumeId);

		// 관련활동 & 경험 정보 수정
        List<ResumeActivityFormDto> resumeActivityFormDtoList = resumeCombinedFormDto.getResumeActivityFormDto();
		
		for(ResumeActivityFormDto resumeActivityFormDto : resumeActivityFormDtoList) {
			if(resumeActivityFormDto.getId() == null) {
				ResumeActivity resumeActivity = resumeActivityFormDto.createResumeActivity();
				resumeActivity.setMember(member);
				resumeActivity.setResume(resume);
				resumeActivityRepository.save(resumeActivity);
				return;
			}
			
			ResumeActivity resumeActivity = resumeActivityRepository.findResumeActivityById(resumeActivityFormDto.getId());
			resumeActivity.update(resumeActivityFormDto);
		}
		
		ResumeActivityFormDto lastResumeActivityFormDto = resumeActivityFormDtoList.get(resumeActivityFormDtoList.size() - 1);
		resumeActivityRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeActivityFormDto.getId() + 1, resumeId);

		// 교육 정보 수정
        List<ResumeEducationFormDto> resumeEducationFormDtoList = resumeCombinedFormDto.getResumeEducationFormDto();
		
		for(ResumeEducationFormDto resumeEducationFormDto : resumeEducationFormDtoList) {
			if(resumeEducationFormDto.getId() == null) {
				ResumeEducation resumeEducation = resumeEducationFormDto.createResumeEducation();
				resumeEducation.setMember(member);
				resumeEducation.setResume(resume);
				resumeEducationRepository.save(resumeEducation);
				return;
			}
			
			ResumeEducation resumeEducation = resumeEducationRepository.findResumeEducationById(resumeEducationFormDto.getId());
			resumeEducation.update(resumeEducationFormDto);
		}
		
		ResumeEducationFormDto lastResumeEducationFormDto = resumeEducationFormDtoList.get(resumeEducationFormDtoList.size() - 1);
		resumeEducationRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumeEducationFormDto.getId() + 1, resumeId);

		// 우대사항 정보 수정
        List<ResumePreferentFormDto> resumePreferentFormDtoList = resumeCombinedFormDto.getResumePreferentFormDto();
		
		for(ResumePreferentFormDto resumePreferentFormDto : resumePreferentFormDtoList) {
			if(resumePreferentFormDto.getId() == null) {
				ResumePreferent resumePreferent = resumePreferentFormDto.createResumePreferent();
				resumePreferent.setMember(member);
				resumePreferent.setResume(resume);
				resumePreferentRepository.save(resumePreferent);
				return;
			}
			
			ResumePreferent resumePreferent = resumePreferentRepository.findResumePreferentById(resumePreferentFormDto.getId());
			resumePreferent.update(resumePreferentFormDto);
		}
		
		ResumePreferentFormDto lastResumePreferentFormDto = resumePreferentFormDtoList.get(resumePreferentFormDtoList.size() - 1);
		resumePreferentRepository.deleteByResumeIdAndIdGreaterThanEqual(lastResumePreferentFormDto.getId() + 1, resumeId);
		
	}
	
	public void deleteResume(Long resumeId) {
		resumeRepository.deleteByResumeId(resumeId);
	}
}
