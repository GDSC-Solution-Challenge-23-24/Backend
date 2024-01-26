// Service  CommentService

package com.example.solution.challenge.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.solution.challenge.Dto.CommentDto;
import com.example.solution.challenge.Dto.CommentListDto;
import com.example.solution.challenge.Dto.CommentUpdateDto;
import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Entity.Comment;
import com.example.solution.challenge.Entity.User;
import com.example.solution.challenge.Repository.BoardRepository;
import com.example.solution.challenge.Repository.CommentRepository;
import com.example.solution.challenge.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 댓글 등록
    public void postComment(CommentDto commentDto) {
        User user= userRepository.findById(commentDto.getUserId()).orElseThrow(NullPointerException::new);
        Board board= boardRepository.findById(commentDto.getBoardId()).orElseThrow(NullPointerException::new);

        Comment newComment= new Comment();
        newComment.setContent(commentDto.getContent());
        newComment.setUser(user);
        newComment.setBoard(board);
        newComment.setDepth(0L); //Long type
        newComment.setParent(0L);
        newComment.setIsDeleted(false);
        commentRepository.save(newComment);
    }

    // 대댓글(자식) 등록
    public void postReplyComment(CommentDto commentDto) {
        User user= userRepository.findById(commentDto.getUserId()).orElseThrow(NullPointerException::new);
        Board board= boardRepository.findById(commentDto.getBoardId()).orElseThrow(NullPointerException::new);

        Comment newReplyComment= new Comment();
        newReplyComment.setContent(commentDto.getContent());
        newReplyComment.setUser(user);
        newReplyComment.setBoard(board);
        newReplyComment.setDepth(1L);	// 대댓글이니 깊이를 1로 설정
        newReplyComment.setParent(commentDto.getParent());

        commentRepository.save(newReplyComment);
    }

    // 게시판 댓글&대댓글 목록
    public List<CommentListDto> getCommentHierarchy(Long boardId) { // 댓글 계층 구조 가져오기

        // 원댓글(부모댓글은 parent가 0) 가져오기
        List<Comment> rootComments = commentRepository.findRootComments(boardId);

        // 원댓글의 대댓글을 재귀적으로 가져오기 (부모의 하위인 자식 댓글 -> 계층 구조)
        List<CommentListDto> rootCommentsDTO = new ArrayList<>();
        for (Comment rootComment : rootComments) {
            CommentListDto rootCommentDTO = convertToDTO(rootComment);
            rootCommentDTO.setChildren(getChildrenCommentsDTO(rootComment.getId()));
            rootCommentsDTO.add(rootCommentDTO);
        }

        // 원댓글 Dto 리턴
        return rootCommentsDTO;
    }

    private List<CommentListDto> getChildrenCommentsDTO(Long parentId) { // 특정 부모 댓글에 속한 자식 댓글을 가져오기

        List<Comment> children = commentRepository.findByParent(parentId); // 부모 댓글 아이디로 검색

        List<CommentListDto> childrenDTO = new ArrayList<>();
        for (Comment child : children) {
            CommentListDto childDTO = convertToDTO(child);
            childDTO.setChildren(getChildrenCommentsDTO(child.getId()));
            childrenDTO.add(childDTO);
        }

        // 자식 댓글 (대댓글) Dto 리턴
        return childrenDTO;
    }

    private CommentListDto convertToDTO(Comment comment) { // 변환
        CommentListDto commentDTO = new CommentListDto();

        commentDTO.setContent(comment.getContent());
        commentDTO.setDepth(comment.getDepth());
        commentDTO.setParent(comment.getParent());
        commentDTO.setIsDeleted(comment.getIsDeleted());
        commentDTO.setUserId(comment.getUser().getUserId());

        return commentDTO;
    }

    // (부모댓글, 자식댓글) 수정 -> 내용만 변경
    public void updateComment(Long comment_id, CommentUpdateDto commentDto) {

        // 댓글 ID에 해당하는 댓글 정보 찾기
        Optional<Comment> optionalComment = commentRepository.findById(comment_id);

        if (optionalComment.isPresent()) {
            Comment existingComment= optionalComment.get();
            existingComment.setContent(commentDto.getContent()); // 존재하는 댓글의 내용 변경

            commentRepository.save(existingComment);
        }
    }

    // (부모댓글, 자식댓글) 삭제
    public void deleteComment(Long comment_id) {
        Optional<Comment> optionalComment = commentRepository.findById(comment_id);
        Long childCount= commentRepository.countByParent(comment_id);

        if (optionalComment.isPresent()) { // 존재하는 경우만 삭제 작업 진행 가능
            if (optionalComment.get().getDepth()==1) { // 자식 댓글이면 바로 삭제
                commentRepository.deleteById(comment_id);
            }
            else // 부모 댓글이면 두 가지 가능성 (자식 유무)
            {
                if (childCount==0L){ // 자식 없는 부모 -> 바로 삭제 가능
                    commentRepository.deleteById(comment_id);
                } else{ // 자식 있는 부모 -> 삭제 여부 1(true)로 변경
                    Comment existingComment= optionalComment.get();
                    existingComment.setIsDeleted(true);
                    commentRepository.save(existingComment);
                }
            }
        }
    }
}