package com.project.fitness.controller;

import com.project.fitness.dto.RecommendationRequestDto;
import com.project.fitness.dto.RecommendationResponseDto;
import com.project.fitness.service.RecommendationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;


    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponseDto> generateRecommendations(@RequestBody RecommendationRequestDto recommendationRequestDto) {
            return ResponseEntity.ok(recommendationService.generateRecommendations(recommendationRequestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationResponseDto>> getUserRecommendations(@PathVariable String userId){
        return ResponseEntity.ok(recommendationService.getUserRecommendations(userId));
    }


    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<RecommendationResponseDto>> getActivityRecommendations(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendations(activityId));
    }
}

