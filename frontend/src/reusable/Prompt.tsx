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
        this.changeResponse(response);
    }

    setSelected(newlySelected: string) {
        this.setState({ selected: newlySelected });
    }

    render() {
        return (
            <div className="w-full max-w-md px-4">
                <Field>
                    <Label className="text-sm/6 font-medium text-black">
                        {this.props.displayText}
                    </Label>
                    {this.promptType({
                        validateAndUpdate: this.validateAndUpdate,
                        choices: this.props.choices,
                        state: this.state,
                        stateManager: this.setSelected,
                    })}
                </Field>
            </div>
        );
    }
}
