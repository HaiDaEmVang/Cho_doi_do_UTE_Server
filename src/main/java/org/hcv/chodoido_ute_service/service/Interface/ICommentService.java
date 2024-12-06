package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.dto.request.CommentRequest;
import org.hcv.chodoido_ute_service.dto.response.CommentDTO;
import org.hcv.chodoido_ute_service.entity.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICommentService {

    CommentDTO findById(Long id);

    Comment findCommentById(Long id);

    CommentDTO add(CommentRequest commentRequest, MultipartFile[] image);

    CommentDTO update(CommentRequest commentRequest, MultipartFile[] image);

    void delete(Long idComment);

    List<CommentDTO> getCommentDTOByProduct(Long idProduct);

    List<CommentDTO> getCommentDTOByUser(Long idUser);

    List<CommentDTO> getCommentedDTOByUser(Long idUser);



}
