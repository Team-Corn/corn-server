package org.hojoon.hackathon.domain.campaign.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CampaignRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String location;

    @NotBlank
    private String time;


    private String fileUrl;

}
