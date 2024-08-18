package com.jobwait.security.exchange;

import java.util.UUID;

abstract public class OAuthExchange {
    abstract public UUID getUserUUID(String someCode);
}
