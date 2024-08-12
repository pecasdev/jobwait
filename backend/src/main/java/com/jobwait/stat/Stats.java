package com.jobwait.stat;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.jobwait.domain.Stat;
import com.jobwait.fault.FaultException;
import com.jobwait.stat.impl.JobTitleStat;

public class Stats {
    // touch this stuff
    public static List<Stat> known = List.of(
            new JobTitleStat("job-title"));

    // don't touch this stuff
    public static Stat statFromId(String statId) {
        List<Stat> matches = known.stream().filter(s -> s.id.equals(statId)).toList();
        if (matches.size() == 0) {
            throw new UnknownStatId(statId);
        }
        return matches.getFirst();
    }

    private static class UnknownStatId extends FaultException {
        private UnknownStatId(String statId) {
            super(HttpStatus.NOT_FOUND, "UNKNOWN_STAT_ID",
                    "statId '%s' does not exist in known stats".formatted(statId));
        }
    }
}
