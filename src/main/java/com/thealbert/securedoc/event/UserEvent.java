package com.thealbert.securedoc.event;

import com.thealbert.securedoc.entity.UserEntity;
import com.thealbert.securedoc.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor

public class UserEvent {

    private UserEntity user;
    private EventType type;
    private Map<?, ?> data;
}
