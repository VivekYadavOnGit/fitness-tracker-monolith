package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequestDto;
import com.project.fitness.dto.RecommendationResponseDto;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public RecommendationResponseDto generateRecommendations(RecommendationRequestDto recommendationRequestDto) {
        User user = userRepository.findById(recommendationRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + recommendationRequestDto.getUserId()));

        Activity activity = activityRepository.findById(recommendationRequestDto.getActivityId())
                .orElseThrow(() -> new IllegalArgumentException("Activity not found with id: " + recommendationRequestDto.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .type(recommendationRequestDto.getType())
                .recommendation(recommendationRequestDto.getRecommendation())
                .instructions(recommendationRequestDto.getInstructions())
                .suggestions(recommendationRequestDto.getSuggestions())
                .safetyTips(recommendationRequestDto.getSafetyTips())
                .activity(activity)
                .build();

        Recommendation savedRecommendation = recommendationRepository.save(recommendation);
        return mapToDto(savedRecommendation);
    }

    private RecommendationResponseDto mapToDto(Recommendation savedRecommendation) {
        RecommendationResponseDto response = new RecommendationResponseDto();
        response.setId(savedRecommendation.getId());
        response.setRecommendation(savedRecommendation.getRecommendation());
        response.setType(savedRecommendation.getType());
        response.setInstructions(savedRecommendation.getInstructions());
        response.setSuggestions(savedRecommendation.getSuggestions());
        response.setSafetyTips(savedRecommendation.getSafetyTips());
        response.setCreatedAt(savedRecommendation.getCreatedAt());
        response.setUpdatedAt(savedRecommendation.getUpdatedAt());
        response.setUserId(savedRecommendation.getUser().getId());
        response.setActivityId(savedRecommendation.getActivity().getId());
        return response;
    }

    public List<RecommendationResponseDto> getUserRecommendations(String userId) {
        List<Recommendation> recommendations = recommendationRepository.findByUserId(userId);
        return recommendations.stream().map(this::mapToDto).toList();
    }

    public List<RecommendationResponseDto> getActivityRecommendations(String activityId) {
        List<Recommendation> recommendations = recommendationRepository.findByActivityId(activityId);
        return recommendations.stream().map(this::mapToDto).toList();
    }
}
