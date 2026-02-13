package com.project.fitness.dto;

import com.project.fitness.model.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequestDto {

    private String recommendation;
    private String type;
    private List<String> instructions;
    private List<String> suggestions;
    private List<String> safetyTips;
    private String userId;
    private String activityId;
}
