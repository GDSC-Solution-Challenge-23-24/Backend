// Repository   CommentRepository

package com.example.solution.challenge.Repository;

import java.util.List;

import com.example.solution.challenge.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.solution.challenge.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.board.id = :boardId AND c.parent = 0")
    List<Comment> findRootComments(@Param("boardId") Long boardId);

    List<Comment> findByParent(Long parentId);

    Long countByParent(Long comment_id);
}