package org.hojoon.hackathon.domain.campaign.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "campaign")
@NoArgsConstructor
public class CampaignEntity {

    // 캠페인 idx
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpIdx;

    // 캠페인 제목
    @Column(name = "title", nullable = false)
    private String title;

    // 캠페인 내용
    @Column(name = "content")
    private String content;

    // 멤버 idx
    @Column(name = "memberIdx")
    private Long memberIdx;

    // 캠페인 기간
    @Column(name = "time")
    private String time;

    // 캠페인 좋아요
    @Column(name = "boardLike")
    private int cpLike;

    // 캠페인 결과, 완료되면 1, 진행 중이라면 0
    private Boolean cpFi;

    // 파일 url
    private String fileUrl;

    // 캠페인 장소
    @Column(name = "location")
    private String location;

    @Builder
    public CampaignEntity(
            String title,
            String content,
            String fileUrl,
            String time,
            String location
    ) {
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
        this.time = time;
        this.location = location;
        this.cpLike = 0;
        this.cpFi = false;
    }

}
