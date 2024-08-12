package com.jobwait.stat;

import com.jobwait.persistence.PostgresController;

import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.persistence.PersistenceController;

public interface AnswerFetching {
    PersistenceController persistence = new PostgresController();

    default List<List<Answer>> fetchAllAnswerSets() {
        return persistence.getAllAnswerSets();
    }
}
