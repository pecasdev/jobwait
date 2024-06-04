import * as React from "react";
import Prompt from "./Prompt";
import { ReactElement } from "react";

export type PromptDefinition = {
    displayText: string;
    idKey: string;
    inputType: "text" | "slider" | "whatever";
};

export default function PromptCollector(props: {
    promptDefinitions: PromptDefinition[];
}) {
    const prompts: ReactElement[] = [];
    for (let promptDefinition of props.promptDefinitions) {
        prompts.push(Prompt(promptDefinition));
    }
    return (
        <div className="border border-black">
            <p className="text-2xl">super cool form you should fill out</p>
            <tbody className="space-y-6">{prompts}</tbody>
        </div>
    );
}
