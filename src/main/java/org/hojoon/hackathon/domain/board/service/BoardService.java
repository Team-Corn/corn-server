package org.hojoon.hackathon.domain.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.board.dto.BoardRequestDTO;
import org.hojoon.hackathon.domain.board.dto.BoardResponseDTO;
import org.hojoon.hackathon.domain.board.entity.BoardEntity;
import org.hojoon.hackathon.domain.board.repository.BoardRepository;
import org.hojoon.hackathon.domain.member.authority.MemberAccountType;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.hojoon.hackathon.global.auth.JwtUtils;
import org.hojoon.hackathon.global.auth.UserSessionHolder;
import org.hojoon.hackathon.global.exception.CustomException;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.hojoon.hackathon.global.exception.CustomErrorCode.BOARD_NOT_EXIST;
import static org.hojoon.hackathon.global.exception.CustomErrorCode.MEMBER_NOT_CORRECT;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserSessionHolder userSessionHolder;
    private final JwtUtils jwtUtils;

    // 게시글 전체 조회
    public List<BoardResponseDTO> getBoards() {
        List<BoardEntity> boardList = boardRepository.findAll();
        return boardList.stream().map(
                BoardResponseDTO::of
        ).toList();
    }

    // 게시글 생성
    public BaseResponse<?> createBoard(BoardRequestDTO requestDTO) {
        MemberEntity curMember = userSessionHolder.current(); // 토큰 정보 가져오기

        BoardEntity board = BoardEntity.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .category(requestDTO.getCategory())
                .memberIdx(curMember.getIdx())
                .fileUrl(requestDTO.getFileUrl())
                .build();
        boardRepository.save(board);
        return BaseResponse.of(
                true,
                "OK",
                "게시글 생성 성공!",
                null
        );
    }

    // 게시글 하나 조회
    public BoardResponseDTO findOneBoard(Long boardIdx) {
        BoardEntity board = boardRepository.findById(boardIdx).orElseThrow(
                () -> new CustomException(BOARD_NOT_EXIST)
        );
        return BoardResponseDTO.of(board);
    }

    // 게시글 수정
    public Long update(Long boardIdx, BoardRequestDTO requestDTO) {
        MemberEntity curMember = userSessionHolder.current();
        BoardEntity board = boardRepository.findById(boardIdx).orElseThrow(
                () -> new CustomException(BOARD_NOT_EXIST)
        );

        if (!board.getMemberIdx().equals(curMember.getIdx())) {
            throw new CustomException(MEMBER_NOT_CORRECT);
        }

        board.update(requestDTO);
        return board.getBoardIdx();
    }

    // 게시글 삭제
    @Transactional
    public BaseResponse<?> deleteBoard(Long boardIdx) {
        MemberEntity curMember = userSessionHolder.current();
        BoardEntity board = boardRepository.findById(boardIdx).orElseThrow(
                () -> new CustomException(BOARD_NOT_EXIST)
        );

        if (!board.getMemberIdx().equals(curMember.getIdx())) {
            throw new CustomException(MEMBER_NOT_CORRECT);
        }

        boardRepository.deleteById(boardIdx);
        return BaseResponse.of(
                true,
                "OK",
                "게시글 삭제 성공!",
                null
        );
    }
}
