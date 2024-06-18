package com.fullstack.repository;

import com.fullstack.model.Followed;
import com.fullstack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowedRepository extends JpaRepository<Followed, Long> {
    List<Followed> findByUser(User user);
}
