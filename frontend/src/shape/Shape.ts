// used to define the shape of data returned from the API
// not the most elegant solution but so long as we're careful, it should be fine
// inb4 "should be replaced with graph-ql", you would not be wrong

export type InvalidShapeErrorProblem =
    | "KEY"
    | "TYPE"
    | "EQUALITY"
    | "INCLUSION";

// note: you can't refactor these two lists into a single list
// the const "string" is different from the type "string"
// every javascript type has a string/literal copy in typescript
export type KnownJavascriptType = [
    "string",
    "number",
    "bigint",
    "boolean",
    "symbol",
    "undefined",
    "object",
    "function",
];
export const KnownJavascriptTypes: KnownJavascriptType = [
    "string",
    "number",
    "bigint",
    "boolean",
    "symbol",
    "undefined",
    "object",
    "function",
];
