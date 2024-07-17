package org.hojoon.hackathon.domain.like.boardLike.repository;

import org.hojoon.hackathon.domain.board.entity.BoardEntity;
import org.hojoon.hackathon.domain.like.boardLike.entity.BoardLikeEntity;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Long> {
    List<BoardLikeEntity> findByBoard(BoardEntity board);
    Optional<BoardLikeEntity> findByMemberAndBoard(MemberEntity member, BoardEntity board);
}
