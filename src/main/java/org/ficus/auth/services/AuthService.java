package org.ficus.auth.services;


import org.ficus.auth.config.BCrypt.BCryptPasswordEncoder;
import org.ficus.auth.data.entity.session.Session;
import org.ficus.auth.exceptions.data.UnregisteredUserException;
import org.ficus.auth.services.parser.AuthorizationHeaderToCredentialParser;
import org.ficus.auth.services.parser.CookieHeaderParser;
import org.ficus.auth.services.parser.Credential;
import org.ficus.data.entity.Role;
import org.ficus.data.entity.Users;
import org.ficus.dto.UserDTO;
import org.ficus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {


    public static final String COOKIE_HEADER_SESSION_ID_NAME = "CATSSESSIONID";
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RedisSessionService redisSessionService;

    private static Credential authenticationHeaderParse(String authenticationHeader) {
        return AuthorizationHeaderToCredentialParser.parse(authenticationHeader);
    }

    public boolean signUp(UserDTO loginDTO, Role role) {
        userService.createUser(loginDTO.getLogin(), passwordEncoder.encode(loginDTO.getPassword()), role);

        return true;
    }


    public CustomResponse signIn(UserDTO loginDTO) {
        Users user = userService.findUserByLogin(loginDTO.getLogin());
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new UnregisteredUserException();
        }
        Session session = createUserSession(loginDTO.getLogin());
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCookieSessionId(session.getSessionId());
        customResponse.setUserId(redisSessionService.getUserIdFromSession(session.getSessionId()));
        return customResponse;
    }


//    //TODO: снести после отладки
//    public ResponseEntity<?> logout(String cookieHeader) {
//        String sessionId = CookieHeaderParser.getSessionIdCookie(cookieHeader, COOKIE_HEADER_SESSION_ID_NAME);
//        RBucket<String> bucket = redisSessionService.getRBucket(sessionId);
//        bucket.delete();
//        return ResponseEntity.ok().build();
//    }

    public Long getUserIdFromCookie(String cookieHeader) {
        String sessionId = CookieHeaderParser.getSessionIdCookie(cookieHeader, COOKIE_HEADER_SESSION_ID_NAME);

        return redisSessionService.getUserIdFromSession(sessionId);
    }

    private Session createUserSession(String login) {
        String sessionId = UUID.randomUUID().toString();
        Users user = userService.findUserByLogin(login);
        return createSession(sessionId, user.getId());

    }


    private Session createSession(String sessionId, long id) {
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserId(id);
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(2));
        redisSessionService.saveSessionToRedis(session);
        return session;
    }


}
