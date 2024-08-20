package com.jobwait.security.exchange;

import java.util.UUID;

import com.jobwait.util.Tuple;

abstract public class OAuthExchange {
    abstract public UUID getUserUUID(String someCode);

    abstract public Tuple<String, UUID> getUUIDAndHash(String someCode);
}
