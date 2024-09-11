package com.jobwait.stat;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.jobwait.domain.Stat;
import com.jobwait.fault.FaultException;
import com.jobwait.stat.impl.JobTitleStat;
import com.jobwait.stat.impl.JobWaitStat;
import com.jobwait.stat.impl.JobWaitWithApplicationCountStat;
import com.jobwait.stat.impl.JobWaitWithEducationStat;
import com.jobwait.stat.impl.JobWaitWithExperienceStat;
import com.jobwait.stat.impl.JobModelAndJobContractStat;

public class Stats {
    // touch this stuff
    public static List<Stat> known = List.of(
            new JobModelAndJobContractStat("job-model-and-job-contract"),
            new JobTitleStat("job-title"),
            new JobWaitStat("job-wait"),
            new JobWaitWithApplicationCountStat("job-wait-with-application-count"),
            new JobWaitWithEducationStat("job-wait-with-education"),
            new JobWaitWithExperienceStat("job-wait-with-experience"));

    // don't touch this stuff
    public static Stat statFromId(String statId) {
        Stat stat = known.stream()
                .filter(s -> s.id.equals(statId))
                .findFirst().orElseThrow(() -> new UnknownStatId(statId));
        return stat;
    }

    private static class UnknownStatId extends FaultException {
        private UnknownStatId(String statId) {
            super(HttpStatus.NOT_FOUND, "UNKNOWN_STAT_ID",
                    "statId '%s' does not exist in known stats".formatted(statId));
        }
    }
}
