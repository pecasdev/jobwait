export type SendUpdateToCollector = {
    sendUpdateToCollector: (_: PromptResponse) => void;
};
export type PromptResponse = string | number | "skipped";
export type PromptDefinition = {
    displayText: string;
    idKey: string;
    inputType: "text" | "slider" | "whatever" | "radio";
};
