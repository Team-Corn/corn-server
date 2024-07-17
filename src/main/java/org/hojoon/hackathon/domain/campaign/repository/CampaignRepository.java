package org.hojoon.hackathon.domain.campaign.repository;

import org.hojoon.hackathon.domain.campaign.entity.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {
}
