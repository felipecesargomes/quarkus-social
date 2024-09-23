package com.felipe.dto;

import com.felipe.domain.Follower;
import lombok.Data;

@Data
public class FollowerResponse {
    private Long id;
    private String name;

    public FollowerResponse() {

    }

    public FollowerResponse(Follower follower) {
         this(follower.getId(), follower.getFollower().getNome());
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
