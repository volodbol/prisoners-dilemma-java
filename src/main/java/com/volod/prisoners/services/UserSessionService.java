package com.volod.prisoners.services;

import com.volod.prisoners.domain.user.User;

public interface UserSessionService {
    User getCurrentUser();
}
