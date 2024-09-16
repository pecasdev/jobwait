package com.jobwait.control;

import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.domain.User;
import com.jobwait.persistence.PersistenceController;
import com.jobwait.persistence.PostgresController;
import com.jobwait.security.AuthToken;
import com.jobwait.security.exchange.LinkedInOAuthExchange;
import com.jobwait.util.Tuple;
import com.jobwait.stat.StatRefreshController;
import com.jobwait.stat.Stats;

public class RequestController {
    private PersistenceController persistence = new PostgresController();
    private StatRefreshController statRefreshController = new StatRefreshController();
    private LinkedInOAuthExchange linkedInOAuthExchange = new LinkedInOAuthExchange();

    public User getUserFromAuthCode(String authCode) {
        String authHash = linkedInOAuthExchange.getUserIDAndHash(authCode).getRight();
        User user = persistence.getUserFromAuthHash(authHash);
        return user;
    }

    public User createUserFromAuthCode(String authCode) {
        Tuple<String, String> userIDAndHash = linkedInOAuthExchange.getUserIDAndHash(authCode);
        User user = persistence.createUser(authHash);
        return user;
    }

    public List<Answer> getUserAnswers(String authCode) {
        User user = getUserFromAuthCode(authCode);
        return persistence.getUserAnswers(user);
    }

    public void submitUserAnswers(String authCode, List<Answer> answers) {
        User user = getUserFromAuthCode(authCode);
        persistence.updateUserAnswers(user, answers);
    }

    public Stat getStat(String statId) {
        Stat stat = Stats.statFromId(statId);
        statRefreshController.refreshStatIfStale(stat.id);
        return stat;
    }

    public void deleteUserAndPurgeAnswers(String authCode) {
        User user = getUserFromAuthCode(authCode);
        persistence.deleteUserAndPurgeAnswers(user);
    }
}
