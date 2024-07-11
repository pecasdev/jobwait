package com.jobwait.persistence;

import com.jobwait.domain.Answers;
import com.jobwait.domain.User;

public abstract class PersistenceController {
    public abstract User getUserFromAuthId(String authId);

    public abstract Answers getUserAnswersFromAuthId(String authId);

    public abstract User updateUserAnswers(User user, Answers answers);

    public abstract User createUserFromAuthId(String authId);

    public abstract void deleteUserAndPurgeAnswers(User user);
}

/*
 * security flow
 * 
 * user creates account using linkedin/google (which gives us an auth token)
 * auth token is sent to backend
 * backend verifies token
 * (https://stackoverflow.com/questions/12296017/how-to-validate-an-oauth-2-0-
 * access-token-for-a-resource-server)
 * token's user_id is hashed into an authHash and is stored alongside a randomly
 * generated UUID under User(id, authHash)
 * future requests must send user UUID and authHash together
 * (note: future requests don't need to resend auth token to backend, client can
 * perform the same conversion to authHash)
 */
