package com.niloy.repository;

import com.niloy.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    // Additional query methods can be defined here if needed
    public List<Story> findByUserId(Integer userId);
}
