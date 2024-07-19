CREATE TABLE questions(
    id SERIAL PRIMARY KEY,
    key TEXT,
    text TEXT,
    answertable TEXT -- refers to a table starting with 'answer_'
);
CREATE TABLE users(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    authhash TEXT UNIQUE -- for hashing API id returned by linkedin/google/whatever
);

CREATE TYPE validworkmodel AS ENUM('ON_SITE', 'HYBRID', 'REMOTE');
CREATE CAST (character varying AS validworkmodel) WITH INOUT AS ASSIGNMENT;

CREATE TYPE validworkcontract AS ENUM(
    'FULL_TIME',
    'PART_TIME',
    'CONTRACT',
    'INTERNSHIP',
    'OTHER'
);
CREATE CAST (character varying AS validworkcontract) WITH INOUT AS ASSIGNMENT;

CREATE TYPE valideducationlevel AS ENUM(
    'LESS_THAN_HIGHSCHOOL',
    'HIGHSCHOOL_DIPLOMA',
    'ASSOCIATE_DEGREE',
    'BACHELOR_DEGREE',
    'MASTER_DEGREE',
    'DOCTORAL_DEGREE',
    'OTHER'
);
CREATE CAST (character varying AS valideducationlevel) WITH INOUT AS ASSIGNMENT;

CREATE TABLE answers(
    userid UUID REFERENCES users(id) UNIQUE,
    answer_jobacceptdate TIMESTAMPTZ,
    answer_jobsearchstartdate TIMESTAMPTZ,
    answer_workmodel validworkmodel,
    answer_workcontract validworkcontract,
    answer_jobapplicationcount INT CHECK (
        answer_jobapplicationcount BETWEEN 0 and 10000
    ),
    answer_jobtitle VARCHAR(50),
    answer_yearsofproexperience INT CHECK (
        answer_yearsofproexperience BETWEEN 0 and 50
    ),
    answer_educationlevel valideducationlevel
);
