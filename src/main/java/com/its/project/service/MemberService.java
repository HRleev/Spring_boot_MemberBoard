package com.its.project.service;


import com.its.project.dto.MemberDTO;
import com.its.project.entity.MemberEntity;
import com.its.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) throws IOException {
        MultipartFile memberFile = memberDTO.getMemberProfile();
        String memberFileName = memberFile.getOriginalFilename();
        memberFileName = System.currentTimeMillis() + "_" + memberFileName;
        String savePath = "D:\\springboot_img\\" + memberFileName;
        if (!memberFile.isEmpty()) {
            memberFile.transferTo(new File(savePath));
            memberDTO.setMemberProfileName((memberFileName));
            MemberEntity memberEntity = MemberEntity.toEntity(memberDTO);
            return memberRepository.save(memberEntity).getId();
        } else {
            return null;
        }
    }

    public MemberEntity duplicateCheck(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isEmpty()) {
            return null;
        } else {
            return optionalMemberEntity.get();
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity =
                memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                return MemberDTO.toDTO(memberEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }
}

