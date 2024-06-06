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
    return (
        <div className="border border-black">
            <p className="text-2xl">super cool form you should fill out</p>
            <tbody className="space-y-6">
                {props.promptDefinitions.map((promptDef, index) => (
                    <Prompt
                        key={index}
                        displayText={promptDef.displayText}
                        idKey={promptDef.idKey}
                        inputType={promptDef.inputType}
                    />
                ))}
            </tbody>
        </div>
    );
}
