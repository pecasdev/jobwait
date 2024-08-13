package com.jobwait.control;

import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.domain.User;
import com.jobwait.persistence.PersistenceController;
import com.jobwait.persistence.PostgresController;
import com.jobwait.security.AuthToken;
import com.jobwait.security.LinkedInOAuthValidator;
import com.jobwait.security.OAuthValidator;
import com.jobwait.stat.Stats;

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

    public List<Answer> getUserAnswers(AuthToken token) {
        User user = getUserFromAuthToken(token);
        return persistence.getUserAnswersFromAuthId(user);
    }

    public void submitUserAnswers(AuthToken token, List<Answer> answers) {
        User user = getUserFromAuthToken(token);
        persistence.updateUserAnswers(user, answers);
    }

    public Stat getStat(String statId) {
        Stat stat = Stats.statFromId(statId);
        return stat;
    }

    public void deleteUserAndPurgeAnswers(AuthToken token) {
        User user = getUserFromAuthToken(token);
        persistence.deleteUserAndPurgeAnswers(user);
    }
}
