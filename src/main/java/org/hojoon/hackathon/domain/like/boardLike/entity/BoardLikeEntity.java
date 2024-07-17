package org.hojoon.hackathon.domain.like.boardLike.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hojoon.hackathon.domain.board.entity.BoardEntity;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;

@Entity(name = "board_like")
@Getter
@NoArgsConstructor
public class BoardLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeIdx;

    @ManyToOne()
    @JoinColumn(name = "fk_member_id")
    private MemberEntity member;

    @ManyToOne()
    @JoinColumn(name = "fk_board_id")
    private BoardEntity board;

    @Builder
    public BoardLikeEntity(Long likeIdx, MemberEntity member, BoardEntity board) {
        this.likeIdx = likeIdx;
        this.member = member;
        this.board = board;

    }
}
