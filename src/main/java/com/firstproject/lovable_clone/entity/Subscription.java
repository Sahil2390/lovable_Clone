package com.firstproject.lovable_clone.entity;
import com.firstproject.lovable_clone.enums.SubscriptionStatus;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {
    Long id;
    User user;
    Plan plan;
    String StripeCustomerId;
    String stripSubscriptionId;
    SubscriptionStatus status;
    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Boolean cancelAtPeriodEnd =false;
    Instant createdAt;
    Instant updatedAt;
}
