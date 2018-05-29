package com.whuthm.gc.auth;

import com.whuthm.gc.Utils;
import com.whuthm.gc.auth.annotation.Token;
import com.whuthm.gc.Constants;
import com.whuthm.gc.domain.User;
import com.whuthm.gc.vo.ApiResponse;
import com.whuthm.gc.vo.LoginRequest;
import com.whuthm.gc.vo.RegisterRequest;
import com.whuthm.gc.repository.UserRepository;
import com.whuthm.gc.vo.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenManager tokenManager;

    @PostMapping(path = "/login")
    public ApiResponse<?> login(@RequestBody LoginRequest request) {
        System.out.println("login:" + request.getUsername());
        User user =  null;
        if (!StringUtils.isEmpty(request.getUsername()) && Utils.matchUsername(request.getUsername())) {
            user = userRepository.findUserByName(request.getUsername());
        }

        if (!StringUtils.isEmpty(request.getEmail()) && Utils.matchEmail(request.getEmail())) {
            user = userRepository.findUserByEmail(request.getEmail());
        }

        if (user != null) {
            if (user.getPassword().equals(request.getPassword())) {
                return ApiResponse.success();
            }
        }

        return ApiResponse.error(ResponseStatus.ILLEGAL_ARGUMENTS);
    }

    @PostMapping(path = "/register")
    public ApiResponse<?> register(@RequestBody RegisterRequest request) {
        System.out.println("register:" + request.getUsername());

        if (!StringUtils.isEmpty(request.getUsername())
                && Utils.matchUsername(request.getUsername())
                && !StringUtils.isEmpty(request.getEmail())
                && Utils.matchEmail(request.getEmail())
                && !StringUtils.isEmpty(request.getPassword())
                && Utils.matchPassword(request.getPassword())) {
            User user = new User();
            user.setId(Utils.getUid());
            user.setName(request.getUsername());
            user.setEmail(request.getEmail());
            user.setRole(User.Role.Normal.getValue());
            user.setSalt(Utils.getUid());
            try {
                user.setPassword(Utils.encryptPassword(request.getPassword(), user.getSalt()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {

        }

        return ApiResponse.success();
    }

    @Token
    @PostMapping(path = "/logout")
    public ApiResponse<?> logout(@RequestHeader(name = Constants.TOKEN) String token) {
        System.out.println("logout:" + token);
        tokenManager.deleteToken(token);
        return ApiResponse.success();
    }

}
