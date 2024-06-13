export type SendUpdateToCollector = {
    sendUpdateToCollector: (_: PromptResponse) => void;
};
export type PromptResponse = string | number | "skipped";
export type PromptDefinition = {
    displayText: string;
    idKey: string;
    inputType: string;
    choices?: string[];
};

export type PromptTypeProps = {
    validateAndUpdate: (response: string) => void;
    choices?: string[];
    state?: any;
    stateManager?: any;
};
