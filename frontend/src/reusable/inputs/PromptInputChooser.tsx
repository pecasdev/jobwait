import * as React from "react";
import ListBoxPrompt from "./ListBoxPrompt";
import { idText } from "typescript";
import { SimplePromptDefinition } from "../PromptTypes";
import TextPrompt from "./TextPrompt";

export function PromptInputChooser(type: string) {
    return (props: SimplePromptDefinition) => {
        switch (type) {
            case "text": {
                const shit = Object.assign({}, props, {
                    choices: ["yes", "no", "maybe so"],
                });
                console.log("shit", shit, props);
                return <ListBoxPrompt idKey={props.idKey} />;
            }
            default: {
                return TextPrompt(props);
            }
        }
    };
}
