package com.volod.prisoners.services.impl;

import com.volod.prisoners.domain.id.UserId;
import com.volod.prisoners.domain.user.User;
import com.volod.prisoners.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
@RequiredArgsConstructor
public class UserSessionServiceImpl implements UserSessionService {

    @Override
    public User getCurrentUser() {
        var attributes = RequestContextHolder.currentRequestAttributes();
        return new User(
                new UserId(attributes.getSessionId())
        );
    }

}
