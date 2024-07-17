package org.hojoon.hackathon.domain.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hojoon.hackathon.domain.board.dto.BoardRequestDTO;
import org.hojoon.hackathon.domain.board.enums.BoardCategory;

@Getter
@Setter
@Entity
@Table(name = "board")
@NoArgsConstructor
public class BoardEntity {

    // 게시물 idx
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    // 게시글 제목
    @Column(name = "title", nullable = false)
    private String title;

    // 게시글 내용
    @Column(name = "content")
    private String content;

    // 게시물 카테고리
    @Column
    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;

    // 멤버 idx
    @Column(name = "memberIdx")
    private Long memberIdx;

    // 게시물 좋아요
    @Column(name = "boardLike")
    private int boardLike;

    private String fileUrl;

    @Builder
    public BoardEntity(
            String title,
            String content,
            BoardCategory category,
            Long memberIdx,
            String fileUrl
    ) {
        this.title = title;
        this.content = content;
        this.boardCategory = category;
        this.memberIdx = memberIdx;
        this.boardLike = 0;
        this.fileUrl = fileUrl;
    }

    public void update(BoardRequestDTO boardRequestDTO) {
        this.title = boardRequestDTO.getTitle();
        this.content = boardRequestDTO.getContent();
    }

    public void likeUpdate(int boardLike) {
        this.boardLike += boardLike;
    }

}
