package com.jobwait.security.exchange;

import com.jobwait.util.Tuple;

abstract public class OAuthExchange {
    abstract public String getUserID(String someCode);

    abstract public Tuple<String, String> getUserIDAndHash(String someCode);
}
