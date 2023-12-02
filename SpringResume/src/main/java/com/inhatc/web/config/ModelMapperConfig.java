package com.inhatc.web.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inhatc.web.dto.ResumeFormDto;
import com.inhatc.web.entity.Resume;

@Configuration
public class ModelMapperConfig {

//	@Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        
//        // 기타 설정 추가 가능
//        modelMapper.addMappings(new PropertyMap<Resume, ResumeFormDto>() {
//    	    protected void configure() {
//    	    	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//    	    	modelMapper.getConfiguration().setSkipNullEnabled(true);
//    	        map().setMemberName(source.getMemberName());
//    	        map().setMemberEmail(source.getMemberEmail());
//    	    }
//    	});
//
//        return modelMapper;
//    }
}
