package org.hojoon.hackathon.domain.like.boardLike.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.board.entity.BoardEntity;
import org.hojoon.hackathon.domain.board.repository.BoardRepository;
import org.hojoon.hackathon.domain.like.boardLike.entity.BoardLikeEntity;
import org.hojoon.hackathon.domain.like.boardLike.repository.BoardLikeRepository;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.hojoon.hackathon.global.auth.UserSessionHolder;
import org.hojoon.hackathon.global.exception.CustomErrorCode;
import org.hojoon.hackathon.global.exception.CustomException;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardLikeService {
    private final UserSessionHolder userSessionHolder;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    public BaseResponse<?> toggle(Long boardIdx) {
        MemberEntity curMember = userSessionHolder.current();

        BoardEntity board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_EXIST));

        Optional<BoardLikeEntity> like = boardLikeRepository.findByMemberAndBoard(curMember, board);

        if (like.isEmpty()) {
            addLike(curMember, board);
            board.likeUpdate(1);
        } else {
            boardLikeRepository.delete(like.get());
            board.likeUpdate(-1);
        }

        return BaseResponse.of(
                true,
                "OK",
                "좋아요 추가/삭제 성공",
                null
        );
    }

    private void addLike(MemberEntity member, BoardEntity board) {
        boardLikeRepository.save(
                BoardLikeEntity.builder()
                        .board(board)
                        .member(member)
                        .build()
        );
    }

//    public BaseResponse<?> getBoardLikes(Long boardIdx) {
//        BoardEntity board = boardRepository.findById(boardIdx)
//                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_EXIST));
//        List<BoardLikeEntity> boardLikes = boardLikeRepository.findByBoard(board);
//        board.likeUpdate(boardLikes.size());
//        return BaseResponse.of(
//                true,
//                "OK",
//                "좋아요 생성/삭제 완료",
//                null
//        );
//    }
}
