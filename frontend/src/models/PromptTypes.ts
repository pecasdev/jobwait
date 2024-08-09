import { ReactNode } from "react";

export type SendUpdateToCollector = {
    sendUpdateToCollector: (_: PromptResponse) => void;
};
export type PromptResponse = string | number | "skipped";
export type PromptDefinition = {
    displayText: string;
    idKey: string;
    inputType: (props: any) => ReactNode;
    choices?: string[];
    max?: number;
};

export type PromptTypeProps = {
    idKey: string;
    labelText: string;
    validateAndUpdate: (value : any) => void;
    choices?: string[];
    form: any;
    max: number;
};
