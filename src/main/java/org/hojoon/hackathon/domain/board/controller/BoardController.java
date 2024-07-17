package org.hojoon.hackathon.domain.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.board.dto.BoardRequestDTO;
import org.hojoon.hackathon.domain.board.dto.BoardResponseDTO;
import org.hojoon.hackathon.domain.board.service.BoardService;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 전체 조회
    @GetMapping("/all")
    public List<BoardResponseDTO> getBoards() {
        return boardService.getBoards();
    }

    // 게시글 등록
    @PostMapping
    public BaseResponse<?> createBoard(@Valid @RequestBody BoardRequestDTO requestDTO) {
        return boardService.createBoard(requestDTO);
    }

    // 게시글 하나 조회
    @GetMapping
    public BoardResponseDTO getBoard(@RequestParam Long boardIdx) {
        return boardService.findOneBoard(boardIdx);
    }

    // 게시글 수정
    @PutMapping
    public Long updateBoard(@RequestParam Long boardIdx, @RequestBody BoardRequestDTO requestDTO) {
        return boardService.update(boardIdx, requestDTO);
    }

    // 게시글 삭제
    @DeleteMapping
    public BaseResponse<?> deleteBoard(@RequestParam Long boardIdx) {
        return boardService.deleteBoard(boardIdx);
    }

}
