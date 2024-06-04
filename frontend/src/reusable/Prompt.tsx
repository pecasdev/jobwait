import * as React from "react";
import { useState } from "react";
import { PromptDefinition } from "./PromptCollector";

export default function Prompt(promptDefinition: PromptDefinition) {
    function changeResponse(text: String) {
        console.log(`response for "${promptDefinition.idKey}" changed to "${text}`)
    }

    const [response, setResponse] = useState("");
    return (
        <div>
            <label className="flex flex-col">
                {promptDefinition.displayText}
                <input
                    className="border border-black border-4"
                    id={promptDefinition.idKey}
                    name="displayText"
                    onChange={(e) => changeResponse(e.target.value)}
                />
            </label>
        </div>
    );
}
