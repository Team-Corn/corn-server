package org.hojoon.hackathon.domain.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hojoon.hackathon.domain.board.enums.BoardCategory;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private BoardCategory category;

    private String fileUrl;

}
