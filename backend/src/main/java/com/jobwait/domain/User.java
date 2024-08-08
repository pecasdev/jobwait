package com.jobwait.domain;

import java.util.UUID;

public record User(UUID id, String authHash) {
}
