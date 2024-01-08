package com.example.solution.challenge.Repository;

import com.example.solution.challenge.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}