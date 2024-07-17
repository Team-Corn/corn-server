package org.hojoon.hackathon.domain.campaign.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hojoon.hackathon.domain.campaign.entity.CampaignEntity;

@Getter
@Builder
@AllArgsConstructor
public class CampaignResponseDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String location;

    @NotBlank
    private String time;

    @NotBlank
    private int cpLike;

    @NotBlank
    private Boolean cpFi;

    private String fileUrl;

    public static CampaignResponseDTO of(CampaignEntity campaign) {
        return CampaignResponseDTO.builder()
                .title(campaign.getTitle())
                .content(campaign.getContent())
                .location(campaign.getLocation())
                .time(campaign.getTime())
                .cpLike(campaign.getCpLike())
                .cpFi(campaign.getCpFi())
                .fileUrl(campaign.getFileUrl())
                .build();
    }

}
