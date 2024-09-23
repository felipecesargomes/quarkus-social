package com.felipe.rest;

import com.felipe.domain.Follower;
import com.felipe.dto.FollowRequest;
import com.felipe.dto.FollowerResponse;
import com.felipe.dto.FollowersPerUserResponse;
import com.felipe.repository.FollowerRepository;
import com.felipe.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

    private final FollowerRepository repository;
    private final UserRepository userRepository;

    @Inject
    public FollowerResource(FollowerRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    @PUT
    public Response followUser(@PathParam("userId") Long userId, FollowRequest request) {

        if(userId.equals(request.getFollowerId())) {
            return Response.status(Response.Status.CONFLICT).entity("Não pode seguir a si mesmo!").build();
        }

        var user = userRepository.findById(userId);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var follower = userRepository.findById(request.getFollowerId());

        boolean followes = repository.followes(follower, user);

        if(!followes) {
            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);

            repository.persist(entity);
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response listFollowers(@PathParam("userId") Long userId) {
        var list = repository.findByUser(userId);
        FollowersPerUserResponse responseObject = new FollowersPerUserResponse();
        responseObject.setFollowersCount(list.size());

        List<FollowerResponse> followerList = list.stream().map(FollowerResponse::new).collect(Collectors.toList());

        responseObject.setContent(followerList);
        return Response.ok(responseObject).build();

    }

    @DELETE
    @Transactional
    public Response unfollowUser(@PathParam("userId") Long userId, @QueryParam("followerId") Long followerId) {
        var user = userRepository.findById(userId);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repository.deleteByFollowerAndUser(followerId, userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
