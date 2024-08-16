package com.jobwait.stat;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jobwait.domain.Stat;

public class StatRefreshController {
    // constants
    private static Duration statLifetime = Duration.ofMinutes(60);

    // stat id to timestamp
    private static Map<String, Instant> generateIdStampMap() {
        return Stats.known.stream().map(s -> s.id)
                .collect(Collectors.toMap(Function.identity(), _ -> Instant.MIN, (_, _) -> {
                    throw new RuntimeException("impossible error");
                }));
    }

    private Map<String, Instant> statIdToLastRefreshStamp = generateIdStampMap();

    public void refreshStatIfStale(String statId) {
        Instant lastRefreshStamp = this.statIdToLastRefreshStamp.get(statId);

        if (Instant.now().isAfter(lastRefreshStamp.plus(statLifetime))) {
            Stat stat = Stats.statFromId(statId);
            stat.fetchAndSetRows();
        }
    }
}
