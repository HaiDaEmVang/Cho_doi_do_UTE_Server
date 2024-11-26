package org.hcv.chodoido_ute_service.mapper;

import org.hcv.chodoido_ute_service.dto.request.CommentRequest;
import org.hcv.chodoido_ute_service.dto.response.CommentDTO;
import org.hcv.chodoido_ute_service.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CommentMapper {
    CommentDTO toCommentDTO(Comment comment);

    Comment toComment(CommentDTO commentDTO);

    Comment toComment(CommentRequest commentRequest);
}
