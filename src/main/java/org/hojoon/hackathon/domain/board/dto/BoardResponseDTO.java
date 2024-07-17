package org.hojoon.hackathon.domain.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hojoon.hackathon.domain.board.entity.BoardEntity;
import org.hojoon.hackathon.domain.board.enums.BoardCategory;


@Getter
@Builder
@AllArgsConstructor
public class BoardResponseDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private BoardCategory category;

    @NotBlank
    private int boardLike;

    public static BoardResponseDTO of(BoardEntity boardEntity){
        return BoardResponseDTO.builder()
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .category(boardEntity.getBoardCategory())
                .boardLike(boardEntity.getBoardLike())
                .build();
    }

}
