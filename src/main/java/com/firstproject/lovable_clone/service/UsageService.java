package com.firstproject.lovable_clone.service;

import com.firstproject.lovable_clone.dto.subscription.PlanLimitResponse;
import com.firstproject.lovable_clone.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitOfUser(Long userId);
}
