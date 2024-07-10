import * as React from "react";
import { Field, Label } from "@headlessui/react";
import {
    PromptDefinition,
    PromptTypeProps,
    SendUpdateToCollector,
} from "./PromptTypes";
import { PromptMapping } from "./inputs/PromptInputMapping";
import TextPrompt from "./inputs/TextPrompt";

type PromptState = {
    selected: string;
};

export default class Prompt extends React.Component<
    PromptDefinition & SendUpdateToCollector,
    PromptState
> {
    promptType: (props: PromptTypeProps) => React.ReactNode;

    constructor(public props: PromptDefinition & SendUpdateToCollector) {
        super(props);

        this.state = {
            selected: "",
        };

        this.promptType = PromptMapping.get(this.props.inputType) ?? TextPrompt;
        this.validateAndUpdate = this.validateAndUpdate.bind(this);
        this.setSelected = this.setSelected.bind(this);
    }

    changeResponse(text: string) {
        // put some "send to backend" code here
        this.props.sendUpdateToCollector(text);
        console.log(`response for "${this.props.idKey}" changed to "${text}"`);
    }

    validateAndUpdate(response: string) {
        //do some validation here
        if (response.trim()) {
            this.changeResponse(response);
        }
    }

    setSelected(newlySelected: string) {
        this.setState({ selected: newlySelected });
    }

    render() {
        return (
            <Field className="grid grid-cols-2 gap-x-4">
                <Label className="w-auto h-auto inline whitespace-normal text-lg/6 font-semibold text-black">
                    {this.props.displayText}
                </Label>
                <div>
                    {this.promptType({
                        validateAndUpdate: this.validateAndUpdate,
                        choices: this.props.choices,
                        state: this.state,
                        stateManager: this.setSelected,
                    })}
                </div>
            </Field>
        );
    }
}
