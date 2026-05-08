package com.firstproject.lovable_clone.dto.subscription;

import com.firstproject.lovable_clone.entity.Plan;

import java.time.Instant;

public record SubscriptionResponse(
        PlanResponse plan,
        String status,
        Instant periodEnd,
        Long tokenUsedThisCycle
) {
}
