package com.jobwait.stat.impl;

import java.util.HashMap;
import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;
import com.jobwait.stat.StatRows;

public class JobModelAndJobContractStat extends Stat implements AnswerFetching {
    public JobModelAndJobContractStat(String id) {
        super(id, "bubble", "Work Model vs Work Contract", "work model", "work contract");
    }

    public void fetchAndSetRows() {
        this.rows = new StatRows();
        HashMap<String, HashMap<String, Integer>> workModelToWorkContractToCount = new HashMap<String, HashMap<String, Integer>>();

        List<List<Answer>> answers = this.fetchAllAnswerSets();
        for (List<Answer> answerSet : answers) {
            String workContract = null;
            String workModel = null;

            for (Answer answer : answerSet) {
                if (answer.questionKey.equals("workmodel")) {
                    workModel = answer.answerValueAsString();
                }

                if (answer.questionKey.equals("workcontract")) {
                    workContract = answer.answerValueAsString();
                }
            }

            if (workModel != null && workContract != null) {
                if (!workModelToWorkContractToCount.containsKey(workModel)) {
                    workModelToWorkContractToCount.put(workModel, new HashMap<String, Integer>());
                }
                HashMap<String, Integer> workContractToCount = workModelToWorkContractToCount.get(workModel);

                if (!workContractToCount.containsKey(workContract)) {
                    workContractToCount.put(workContract, 0);
                }
                int count = workContractToCount.get(workContract);

                workContractToCount.put(workContract, count + 1);
            }
        }

        for (String workModel : workModelToWorkContractToCount.keySet()) {
            HashMap<String, Integer> workContractToCount = workModelToWorkContractToCount.get(workModel);
            for (String workContract : workContractToCount.keySet()) {
                int count = workContractToCount.get(workContract);

                HashMap<String, Object> bubble = new HashMap<String, Object>();
                bubble.put("x", workModel);
                bubble.put("y", workContract);
                bubble.put("count", count);

                // for bubble graphs, we don't need a key-value pair, it's just a list of bubbles
                // unfortunately the abstraction doesn't hold well here because we're forced to use a hashmap
                // so here we just use a unique key for each element and treat it like a list
                // on the frontend we will mold this data into the right shape
                this.rows.put(workModel.concat(workContract), bubble);
            }
        }

    }
}
