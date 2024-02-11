// Repository  BoardRepository


package com.example.solution.challenge.Repository;

import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUser(User user);
}