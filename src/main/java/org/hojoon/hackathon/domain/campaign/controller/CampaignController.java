package org.hojoon.hackathon.domain.campaign.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.campaign.dto.CampaignRequestDTO;
import org.hojoon.hackathon.domain.campaign.dto.CampaignResponseDTO;
import org.hojoon.hackathon.domain.campaign.service.CampaignService;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/campaign")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    // 캠페인 전체 조회
    @GetMapping("/all")
    public List<CampaignResponseDTO> getCampaigns() {
        return campaignService.getCampaigns();
    }

    // 캠페인 등록
    @PostMapping
    public BaseResponse<?> createCampaign(@Valid @RequestBody CampaignRequestDTO requestDTO) {
        return campaignService.createdCampaign(requestDTO);
    }

    // 캠페인 하나 조회
    @GetMapping
    public CampaignResponseDTO getCampaign(@RequestParam Long cpIdx) {
        return campaignService.findOneCampaign(cpIdx);
    }

    // 캠페인 삭제
    @DeleteMapping
    public BaseResponse<?> deleteCampaign(@RequestParam Long cpIdx) {
        return campaignService.deleteCampaign(cpIdx);
    }

}
