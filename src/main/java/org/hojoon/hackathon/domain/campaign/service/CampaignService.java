package org.hojoon.hackathon.domain.campaign.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.campaign.dto.CampaignRequestDTO;
import org.hojoon.hackathon.domain.campaign.dto.CampaignResponseDTO;
import org.hojoon.hackathon.domain.campaign.entity.CampaignEntity;
import org.hojoon.hackathon.domain.campaign.repository.CampaignRepository;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.hojoon.hackathon.global.auth.JwtUtils;
import org.hojoon.hackathon.global.auth.UserSessionHolder;
import org.hojoon.hackathon.global.exception.CustomErrorCode;
import org.hojoon.hackathon.global.exception.CustomException;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hojoon.hackathon.global.exception.CustomErrorCode.CAMPAIGN_NOT_EXIST;
import static org.hojoon.hackathon.global.exception.CustomErrorCode.MEMBER_NOT_CORRECT;

@Service
@Transactional
@RequiredArgsConstructor
public class CampaignService {

    private final UserSessionHolder userSessionHolder;
    private final JwtUtils jwtUtils;
    private final CampaignRepository campaignRepository;

    // 캠페인 전체 조회
    public List<CampaignResponseDTO> getCampaigns() {
        List<CampaignEntity> campaignList = campaignRepository.findAll();
        return campaignList.stream().map(
                CampaignResponseDTO::of
        ).toList();
    }

    // 캠페인 등록
    public BaseResponse<?> createdCampaign(CampaignRequestDTO requestDTO) {
        MemberEntity curMember = userSessionHolder.current();
        CampaignEntity campaign = CampaignEntity.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .time(requestDTO.getTime())
                .location(requestDTO.getLocation())
                .fileUrl(requestDTO.getFileUrl())
                .build();
        campaignRepository.save(campaign);

        return BaseResponse.of(
                true,
                "OK",
                "캠페인 등록 성공",
                null
        );
    }

    // 캠페인 하나 조회
    public CampaignResponseDTO findOneCampaign(Long cpIdx) {
        CampaignEntity campaign = campaignRepository.findById(cpIdx).orElseThrow(
                () -> new CustomException(CAMPAIGN_NOT_EXIST)
        );
        return CampaignResponseDTO.of(campaign);
    }

    @Transactional
    public BaseResponse<?> deleteCampaign(Long cpIdx) {
        MemberEntity curMember = userSessionHolder.current();
        CampaignEntity campaign = campaignRepository.findById(cpIdx).orElseThrow(
                () -> new CustomException(CAMPAIGN_NOT_EXIST)
        );

        if (!campaign.getMemberIdx().equals(curMember.getIdx())) {
            throw new CustomException(MEMBER_NOT_CORRECT);
        }

        campaignRepository.deleteById(cpIdx);

        return BaseResponse.of(
                true,
                "OK",
                "캠페인 삭제 성공!",
                null
        );

    }
}
