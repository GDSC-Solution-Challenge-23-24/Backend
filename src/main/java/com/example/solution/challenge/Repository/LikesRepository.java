package com.example.solution.challenge.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.solution.challenge.Entity.Likes;
import com.example.solution.challenge.Entity.User;
import com.example.solution.challenge.Entity.Board;


@Transactional
public interface LikesRepository extends JpaRepository<Likes, Long> {
    // 좋아요 여부 체크
    Optional<Object> findByUserAndBoard(User user, Board board);
    // 좋아요 삭제
    void deleteByUserAndBoard(User user, Board board);
    // 마이페이지 추가
    List<Likes> findByUser(User user);
}
