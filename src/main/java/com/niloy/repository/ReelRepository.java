package com.niloy.repository;

import com.niloy.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ReelRepository extends JpaRepository<Reels, Integer> {
    public List<Reels> findByUserId(Integer userId);
}
