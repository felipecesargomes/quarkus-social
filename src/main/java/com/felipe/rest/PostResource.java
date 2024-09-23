package com.felipe.rest;

import com.felipe.domain.Post;
import com.felipe.domain.User;
import com.felipe.dto.CreatePostRequest;
import com.felipe.dto.PostResponse;
import com.felipe.repository.FollowerRepository;
import com.felipe.repository.PostRepository;
import com.felipe.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private UserRepository userRepository;
    private PostRepository repository;
    private final FollowerRepository followerRepository;

    @Inject
    public PostResource(UserRepository userRepository, PostRepository repository, FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.repository = repository;
        this.followerRepository = followerRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest request) {
        User user = userRepository.findById(userId);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());
        repository.persist(post);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPost(@PathParam("userId") Long userId, @HeaderParam("followerId") Long followerId) {
        User user = userRepository.findById(userId);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(followerId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Você esqueceu o cabeçalho")
                    .build();
        }

        User follower = userRepository.findById(followerId);

        if(follower == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Não existe followerId informado")
                    .build();
        }

        boolean follows = followerRepository.followes(follower, user);
        if(!follows) {
            return Response.status(Response.Status.FORBIDDEN).entity("Você não consegue ver os posts").build();
        }

        PanacheQuery<Post> query = repository.find("user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = query.list();
        List<PostResponse> collect = list.stream().map(post -> PostResponse.fromEntity(post)).collect(Collectors.toList());
        return Response.ok(collect).build();
    }



}
