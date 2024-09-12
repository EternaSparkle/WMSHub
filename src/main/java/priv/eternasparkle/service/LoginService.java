package priv.eternasparkle.service;


import priv.eternasparkle.entity.User;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    User login(String identity, String credential, HttpServletResponse response);
}
