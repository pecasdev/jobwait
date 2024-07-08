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
CREATE TABLE answer_jobacceptdate(
    userid UUID REFERENCES users(id) UNIQUE,
    response TIMESTAMPTZ
);
CREATE TABLE answer_jobsearchstartdate(
    userid UUID REFERENCES users(id) UNIQUE,
    response TIMESTAMPTZ
);
CREATE TYPE validworkmodel AS ENUM('ON_SITE', 'HYBRID', 'REMOTE');
CREATE CAST (character varying AS validworkmodel) WITH INOUT AS ASSIGNMENT;
CREATE TABLE answer_workmodel(
    userid UUID REFERENCES users(id) UNIQUE,
    response validworkmodel
);
CREATE TYPE validworkcontract AS ENUM(
    'FULL_TIME',
    'PART_TIME',
    'CONTRACT',
    'INTERNSHIP',
    'OTHER'
);
CREATE CAST (character varying AS validworkcontract) WITH INOUT AS ASSIGNMENT;
CREATE TABLE answer_workcontract(
    userid UUID REFERENCES users(id) UNIQUE,
    response validworkcontract
);
CREATE TABLE answer_jobapplicationcount(
    userid UUID REFERENCES users(id) UNIQUE,
    response INT,
    CHECK (
        response BETWEEN 0 and 10000
    )
);
CREATE TABLE answer_jobtitle(
    userid UUID REFERENCES users(id) UNIQUE,
    response VARCHAR(50)
);
CREATE TABLE answer_yearsofproexperience(
    userid UUID REFERENCES users(id) UNIQUE,
    response INT,
    CHECK (
        response BETWEEN 0 and 50
    )
);
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
CREATE TABLE answer_educationlevel(
    userid UUID REFERENCES users(id) UNIQUE,
    response valideducationlevel
);
