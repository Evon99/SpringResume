package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeCareer;
import com.inhatc.web.entity.ResumeForeign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeForeignFormDto {
	
    private Long id; //기본키
    
    private String countryName; // 국가명
    
//    private String foreignDuration; // 해외 경험 기간
    
    private String foreignDurationStartYear; // 해외 기간 시작 년도
	
	private String foreignDurationStartMonth; // 해외 기간 시작 월

	private String foreignDurationEndYear; // 해외 기간 끝 년도
	
	private String foreignDurationEndMonth; // 해외 기간 끝 월
	
    private String foreignAgencyName; // 해외 기관명
    
    private String foreignHistory; // 해외 경험 내용
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeForeign createResumeForeign() {
    	return modelMapper.map(this, ResumeForeign.class);
    }
    
    public static List<ResumeForeignFormDto> listOf(List<ResumeForeign> resumeForeignList) {
        List<ResumeForeignFormDto> result = new ArrayList<>();

        for (ResumeForeign resumeForeign : resumeForeignList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeForeign, ResumeForeignFormDto> typeMap = modelMapper.getTypeMap(ResumeForeign.class, ResumeForeignFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeForeign.class, ResumeForeignFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumeForeign, ResumeForeignFormDto.class));
        }

        return result;
    }
}
