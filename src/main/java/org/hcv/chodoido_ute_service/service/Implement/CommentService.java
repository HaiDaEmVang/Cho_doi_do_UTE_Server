package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.CommentRequest;
import org.hcv.chodoido_ute_service.dto.response.CommentDTO;
import org.hcv.chodoido_ute_service.entity.Comment;
import org.hcv.chodoido_ute_service.entity.CommentImg;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.entity.User;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.CommentMapper;
import org.hcv.chodoido_ute_service.repository.CommentRepository;
import org.hcv.chodoido_ute_service.service.AwsS3Service;
import org.hcv.chodoido_ute_service.service.Interface.ICommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService implements ICommentService {
    CommentMapper commentMapper;
    CommentRepository commentRepository;
    ProductService productService;
    UserService userService;
    AwsS3Service awsS3Service;
    private String URL_IMAGE_COMMENT_DEFAUTL = "images/comment";

    @Override
    public CommentDTO findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Không tìm thấy comment với id: "+ id));
        return commentMapper.toCommentDTO(comment);
    }

    @Override
    public Comment findCommentById(Long id) {
        return  commentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Không tìm thấy comment với id: "+ id));
    }

    @Override
    public CommentDTO add(CommentRequest commentRequest, MultipartFile[] image) {
        commentRequest.setId(0L);
        Product product = productService.findById(commentRequest.getIdProduct());
        User user = userService.findByID(commentRequest.getIdUser());
        if(commentRepository.existsCommentByUserAndProduct(user, product) != null)
            throw new NoActionException("Comment Đã tồn tại");
        Comment comment = new Comment();
        comment = commentMapper.toComment(commentRequest);
        comment.setUser(user);
        comment.setProduct(product);
        comment.setTimePost(LocalDateTime.now());
        for(MultipartFile file : image) {
            CommentImg commentImg = new CommentImg();
            commentImg.setUrlImg(awsS3Service.saveImageToS3(file, URL_IMAGE_COMMENT_DEFAUTL));
            commentImg.setComment(comment);
            comment.getImages().add(commentImg);
        }

        return commentMapper.toCommentDTO(commentRepository.save(comment));
    }

    @Override
    public CommentDTO update(CommentRequest commentRequest, MultipartFile[] image) {
        return null;
    }

    @Override
    public void delete(Long idComment) {
        commentRepository.deleteById(idComment);
    }

    @Override
    public List<CommentDTO> getCommentDTOByProduct(Long idProduct) {
        return commentRepository.findCommentByProduct(productService.findById(idProduct)).stream().map(commentMapper::toCommentDTO).toList();
    }

    @Override
    public List<CommentDTO> getCommentDTOByUser(Long idUser) {
        return commentRepository.findCommentByUser(userService.findByID(idUser)).stream().map(commentMapper::toCommentDTO).toList();
    }

    @Override
    public List<CommentDTO> getCommentedDTOByUser(Long idUser) {
        return commentRepository.findCommentedByUser(userService.findByID(idUser)).stream().map(commentMapper::toCommentDTO).toList();
    }
}
