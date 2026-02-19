package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequestDto;
import com.project.fitness.dto.RecommendationResponseDto;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public RecommendationResponseDto generateRecommendations(
            RecommendationRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found: " + dto.getUserId()));

        Activity activity = activityRepository.findById(dto.getActivityId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Activity not found: " + dto.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .type(dto.getType())
                .recommendation(dto.getRecommendation())
                .instructions(dto.getInstructions())
                .suggestions(dto.getSuggestions())
                .safetyTips(dto.getSafetyTips())
                .build();

        Recommendation saved = recommendationRepository.save(recommendation);

        return mapToDto(saved);
    }

    private RecommendationResponseDto mapToDto(Recommendation r) {

        RecommendationResponseDto response = new RecommendationResponseDto();

        response.setId(r.getId());
        response.setRecommendation(r.getRecommendation());
        response.setType(r.getType());
        response.setInstructions(r.getInstructions());
        response.setSuggestions(r.getSuggestions());
        response.setSafetyTips(r.getSafetyTips());
        response.setCreatedAt(r.getCreatedAt());
        response.setUpdatedAt(r.getUpdatedAt());
        response.setUserId(r.getUser().getId());
        response.setActivityId(r.getActivity().getId());

        return response;
    }

    public List<RecommendationResponseDto> getUserRecommendations(String userId) {

        List<Recommendation> recommendations =
                recommendationRepository.findByUserId(userId);

        return recommendations.stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<RecommendationResponseDto> getActivityRecommendations(String activityId) {

        List<Recommendation> recommendations =
                recommendationRepository.findByActivityId(activityId);

        return recommendations.stream()
                .map(this::mapToDto)
                .toList();
    }
}
