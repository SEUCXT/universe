package com.seu.universe.service;

import com.seu.universe.config.ViewObject;

public interface UserService {

    ViewObject getVertificationCode(String email);

    ViewObject userRegister(String email, String nickname, String password);
}
