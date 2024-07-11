package com.jobwait.control;

import com.jobwait.domain.Answers;
import com.jobwait.domain.User;
import com.jobwait.persistence.PersistenceController;
import com.jobwait.persistence.PostgresController;
import com.jobwait.security.AuthToken;
import com.jobwait.security.LinkedInOAuthValidator;
import com.jobwait.security.OAuthValidator;

public class RequestController {
    private PersistenceController persistence = new PostgresController();
    private OAuthValidator oAuthValidator = new LinkedInOAuthValidator();

    public User getUserFromAuthToken(AuthToken token) {
        oAuthValidator.validateToken(token);
        User user = persistence.getUserFromAuthId(token.clientId());
        return user;
    }

    public User createUserFromAuthToken(AuthToken token) {
        oAuthValidator.validateToken(token);
        User user = persistence.createUserFromAuthId(token.clientId());
        return user;
    }

    public Answers getUserAnswers(AuthToken token) {
        oAuthValidator.validateToken(token);
        return persistence.getUserAnswersFromAuthId(token.clientId());
    }

    public User submitUserAnswers(AuthToken token, Answers answers) {
        User user = getUserFromAuthToken(token);
        return persistence.updateUserAnswers(user, answers);
    }

    public void deleteUserAndPurgeAnswers(AuthToken token) {
        User user = getUserFromAuthToken(token);
        persistence.deleteUserAndPurgeAnswers(user);
    }
}
