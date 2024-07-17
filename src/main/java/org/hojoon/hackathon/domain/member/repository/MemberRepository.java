package org.hojoon.hackathon.domain.member.repository;

import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    boolean existsById(String idx);

    Optional<MemberEntity> findById(String id);

    Optional<MemberEntity> findByEmail(String email);
}
