import { gql } from "../gql";

export const USER_BY_ID = gql(`
    query UserById($id: ID!) {
        userById(userID: $id) {
            userID
            prompts {
                promptID
                question
                response {
                    __typename
                    ...SomeIntResponse
                    ...SomeStrResponse
                }
            }
        }
    }

    fragment SomeIntResponse on IntResponse {
        intResponse
    }
    fragment SomeStrResponse on StrResponse {
        strResponse
    }
`);

export const ALL_USERS = gql(`
    query AllUsers {
        getAllUsers {
            userID
            prompts {
                promptID
                question
                response {
                    __typename
                    ...SomeIntResponse
                    ...SomeStrResponse
                }
            }
        }
    }

    fragment SomeIntResponse on IntResponse {
        intResponse
    }
    fragment SomeStrResponse on StrResponse {
        strResponse
    }
`);

export const ADD_USER = gql(`
    mutation AddUser($name: String!) {
        addUser(name: $name) {
            userID
        }
    }
`);
