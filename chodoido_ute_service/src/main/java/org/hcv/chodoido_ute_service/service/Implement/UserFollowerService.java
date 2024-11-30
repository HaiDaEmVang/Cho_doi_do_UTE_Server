package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.response.FollowDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.Follower;
import org.hcv.chodoido_ute_service.entity.User;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.FollowMapper;
import org.hcv.chodoido_ute_service.mapper.UserMapper;
import org.hcv.chodoido_ute_service.repository.FollowerRepository;
import org.hcv.chodoido_ute_service.service.Interface.IUserFollower;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFollowerService implements IUserFollower {
    FollowerRepository followerRepository;
    UserService userService;
    UserMapper userMapper;
    FollowMapper followMapper;

    @Override
    public FollowDTO addFollower(Long idFollow, Long idFollower) {
        if(idFollow == null || idFollower == null)
            throw new NoActionException("idFollow or idFollower is null");
        User userFollow = userService.findByID(idFollow);
        User userFollower = userService.findByID(idFollower);
        return followMapper.toFollowDTO(followerRepository.save(new Follower((long) -1, userFollow, userFollower)));
    }

    @Override
    public void removeFollower(Long idFollow, Long idFollower) {
        if(idFollow == null || idFollower == null)
            throw new NoActionException("idFollow or idFollower is null");
        User userFollow = userService.findByID(idFollow);
        User userFollower = userService.findByID(idFollower);
        Follower follower = followerRepository.findByUserFollowAndUserFollower(userFollow, userFollower)
                .orElseThrow(()-> new NotFoundException("User: " + userFollow.getId() + " is not followed by user: " + userFollower.getId()));
        followerRepository.delete(follower);
    }

    @Override
    public List<UserDTO> findListUserFollowers(Long idFollow) {
        if(idFollow == null)
            throw new NoActionException("idFollow is null");
        List<User> list = followerRepository.listFollowerByUser(idFollow);
        return  userMapper.toUserResponseList(list);
    }


    @Override
    public int countFollowers(Long idFollow) {
        if(idFollow == null)
            throw new NoActionException("idFollow is null");
        return followerRepository.countFollowerByUser(idFollow);
    }

    @Override
    public FollowDTO isFollow(Long idFollow, Long idFollower) {
        if(idFollow == null || idFollower == null)
            throw new NoActionException("idFollow is null");
        return followMapper.toFollowDTO(followerRepository.isFollow(idFollow, idFollower));

    }


}
