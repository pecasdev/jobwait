/* eslint-disable */
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type IntResponse = {
  __typename?: 'IntResponse';
  intResponse: Scalars['Int']['output'];
};

export type Mutation = {
  __typename?: 'Mutation';
  addUser?: Maybe<User>;
};


export type MutationAddUserArgs = {
  name: Scalars['String']['input'];
};

export type Prompt = {
  __typename?: 'Prompt';
  promptID: Scalars['ID']['output'];
  question: Scalars['String']['output'];
  response?: Maybe<Response>;
};

export type Query = {
  __typename?: 'Query';
  getAllUsers: Array<Maybe<User>>;
  promptById?: Maybe<Prompt>;
  userById?: Maybe<User>;
};


export type QueryPromptByIdArgs = {
  promptID: Scalars['ID']['input'];
};


export type QueryUserByIdArgs = {
  userID: Scalars['ID']['input'];
};

export type Response = IntResponse | StrResponse;

export type StrResponse = {
  __typename?: 'StrResponse';
  strResponse: Scalars['String']['output'];
};

export type User = {
  __typename?: 'User';
  prompts: Array<Prompt>;
  userID: Scalars['ID']['output'];
};

export type UserByIdQueryVariables = Exact<{
  id: Scalars['ID']['input'];
}>;


export type UserByIdQuery = { __typename?: 'Query', userById?: { __typename?: 'User', userID: string, prompts: Array<{ __typename?: 'Prompt', promptID: string, question: string, response?: (
        { __typename: 'IntResponse' }
        & { ' $fragmentRefs'?: { 'SomeIntResponseFragment': SomeIntResponseFragment } }
      ) | (
        { __typename: 'StrResponse' }
        & { ' $fragmentRefs'?: { 'SomeStrResponseFragment': SomeStrResponseFragment } }
      ) | null }> } | null };

export type SomeIntResponseFragment = { __typename?: 'IntResponse', intResponse: number } & { ' $fragmentName'?: 'SomeIntResponseFragment' };

export type SomeStrResponseFragment = { __typename?: 'StrResponse', strResponse: string } & { ' $fragmentName'?: 'SomeStrResponseFragment' };

export type AllUsersQueryVariables = Exact<{ [key: string]: never; }>;


export type AllUsersQuery = { __typename?: 'Query', getAllUsers: Array<{ __typename?: 'User', userID: string, prompts: Array<{ __typename?: 'Prompt', promptID: string, question: string, response?: (
        { __typename: 'IntResponse' }
        & { ' $fragmentRefs'?: { 'SomeIntResponseFragment': SomeIntResponseFragment } }
      ) | (
        { __typename: 'StrResponse' }
        & { ' $fragmentRefs'?: { 'SomeStrResponseFragment': SomeStrResponseFragment } }
      ) | null }> } | null> };

export type AddUserMutationVariables = Exact<{
  name: Scalars['String']['input'];
}>;


export type AddUserMutation = { __typename?: 'Mutation', addUser?: { __typename?: 'User', userID: string } | null };

export const SomeIntResponseFragmentDoc = {"kind":"Document","definitions":[{"kind":"FragmentDefinition","name":{"kind":"Name","value":"SomeIntResponse"},"typeCondition":{"kind":"NamedType","name":{"kind":"Name","value":"IntResponse"}},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"intResponse"}}]}}]} as unknown as DocumentNode<SomeIntResponseFragment, unknown>;
export const SomeStrResponseFragmentDoc = {"kind":"Document","definitions":[{"kind":"FragmentDefinition","name":{"kind":"Name","value":"SomeStrResponse"},"typeCondition":{"kind":"NamedType","name":{"kind":"Name","value":"StrResponse"}},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"strResponse"}}]}}]} as unknown as DocumentNode<SomeStrResponseFragment, unknown>;
export const UserByIdDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"UserById"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"id"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"ID"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"userById"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"userID"},"value":{"kind":"Variable","name":{"kind":"Name","value":"id"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"userID"}},{"kind":"Field","name":{"kind":"Name","value":"prompts"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"promptID"}},{"kind":"Field","name":{"kind":"Name","value":"question"}},{"kind":"Field","name":{"kind":"Name","value":"response"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"__typename"}},{"kind":"FragmentSpread","name":{"kind":"Name","value":"SomeIntResponse"}},{"kind":"FragmentSpread","name":{"kind":"Name","value":"SomeStrResponse"}}]}}]}}]}}]}},{"kind":"FragmentDefinition","name":{"kind":"Name","value":"SomeIntResponse"},"typeCondition":{"kind":"NamedType","name":{"kind":"Name","value":"IntResponse"}},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"intResponse"}}]}},{"kind":"FragmentDefinition","name":{"kind":"Name","value":"SomeStrResponse"},"typeCondition":{"kind":"NamedType","name":{"kind":"Name","value":"StrResponse"}},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"strResponse"}}]}}]} as unknown as DocumentNode<UserByIdQuery, UserByIdQueryVariables>;
export const AllUsersDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"AllUsers"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"getAllUsers"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"userID"}},{"kind":"Field","name":{"kind":"Name","value":"prompts"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"promptID"}},{"kind":"Field","name":{"kind":"Name","value":"question"}},{"kind":"Field","name":{"kind":"Name","value":"response"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"__typename"}},{"kind":"FragmentSpread","name":{"kind":"Name","value":"SomeIntResponse"}},{"kind":"FragmentSpread","name":{"kind":"Name","value":"SomeStrResponse"}}]}}]}}]}}]}},{"kind":"FragmentDefinition","name":{"kind":"Name","value":"SomeIntResponse"},"typeCondition":{"kind":"NamedType","name":{"kind":"Name","value":"IntResponse"}},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"intResponse"}}]}},{"kind":"FragmentDefinition","name":{"kind":"Name","value":"SomeStrResponse"},"typeCondition":{"kind":"NamedType","name":{"kind":"Name","value":"StrResponse"}},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"strResponse"}}]}}]} as unknown as DocumentNode<AllUsersQuery, AllUsersQueryVariables>;
export const AddUserDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"AddUser"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"name"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"String"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"addUser"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"name"},"value":{"kind":"Variable","name":{"kind":"Name","value":"name"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"userID"}}]}}]}}]} as unknown as DocumentNode<AddUserMutation, AddUserMutationVariables>;