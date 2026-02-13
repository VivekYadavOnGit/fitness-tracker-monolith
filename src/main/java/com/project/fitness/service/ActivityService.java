package com.project.fitness.service;

import com.project.fitness.dto.ActivityRequestDto;
import com.project.fitness.dto.ActivityResponseDto;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    public ActivityResponseDto trackActivity(ActivityRequestDto activityRequestDto) {
        User user = userRepository.findById(activityRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + activityRequestDto.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(activityRequestDto.getType())
                .additionalMatrics(activityRequestDto.getAdditionalMatrics())
                .duration(activityRequestDto.getDuration())
                .caloriesBurned(activityRequestDto.getCaloriesBurned())
                .startTime(activityRequestDto.getStartTime())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToDto(savedActivity);
    }

    private ActivityResponseDto mapToDto(Activity savedActivity) {
        ActivityResponseDto response = new ActivityResponseDto();
        response.setId(savedActivity.getId());
        response.setUserId(savedActivity.getUser().getId());
        response.setType(savedActivity.getType());
        response.setAdditionalMatrics(savedActivity.getAdditionalMatrics());
        response.setDuration(savedActivity.getDuration());
        response.setCaloriesBurned(savedActivity.getCaloriesBurned());
        response.setStartTime(savedActivity.getStartTime());
        response.setCreatedAt(savedActivity.getCreatedAt());
        response.setUpdatedAt(savedActivity.getUpdatedAt());
        return response;
    }

    public List<ActivityResponseDto> getUserActivities(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream().map(this::mapToDto).toList();
    }
}
