package com.project.fitness.dto;

import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponseDto {

    private String id;
    private String recommendation;
    private String type;
    private List<String> instructions;
    private List<String> suggestions;
    private List<String> safetyTips;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userId;
    private String activityId;
}
