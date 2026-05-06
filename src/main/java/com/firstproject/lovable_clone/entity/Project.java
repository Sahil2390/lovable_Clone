package com.firstproject.lovable_clone.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.logging.Level;
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
    Long id;
    String name;
    User owner;
    Boolean isPublic=false;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
