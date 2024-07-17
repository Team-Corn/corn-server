package org.hojoon.hackathon.domain.board.repository;

import org.hojoon.hackathon.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}