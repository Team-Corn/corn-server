package org.hojoon.hackathon.domain.like.boardLike.controller;

import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.like.boardLike.service.BoardLikeService;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardLikes")
@RequiredArgsConstructor
public class BoardLikeController {
    private final BoardLikeService boardLikeService;

//    @GetMapping
//    public BaseResponse<?> getBoardLikes(Long boardIdx) {
//        return boardLikeService.getBoardLikes(boardIdx);
//    }

    @PatchMapping
    public BaseResponse<?> patch(@RequestParam Long boardIdx) {
        return boardLikeService.toggle(boardIdx);
    }

}
