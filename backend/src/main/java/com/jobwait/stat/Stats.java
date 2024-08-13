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
    private static StatRefreshOfficer statRefreshOfficer = new StatRefreshOfficer();

    public static Stat statFromId(String statId) {
        Stat stat = known.stream()
                .filter(s -> s.id.equals(statId))
                .findFirst().orElseThrow(() -> new UnknownStatId(statId));
        Stats.statRefreshOfficer.refreshStatIfNeeded(stat.id);
        return stat;
    }

    private static class UnknownStatId extends FaultException {
        private UnknownStatId(String statId) {
            super(HttpStatus.NOT_FOUND, "UNKNOWN_STAT_ID",
                    "statId '%s' does not exist in known stats".formatted(statId));
        }
    }
}
