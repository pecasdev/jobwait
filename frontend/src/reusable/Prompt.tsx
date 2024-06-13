import * as React from "react";
import debounceFunction from "../util/debounceFunction";
import { Field, Label } from "@headlessui/react";
import {
    PromptDefinition,
    SendUpdateToCollector,
    SimplePromptDefinition,
} from "./PromptTypes";
import { PromptMapping } from "./inputs/PromptInputMapping";
import TextPrompt from "./inputs/TextPrompt";
import { LoadingIcon } from "./LoadingIcon";

export default class Prompt extends React.Component {
    debounced: (...args: any[]) => void;
    promptType: (props: SimplePromptDefinition) => React.ReactNode;
    showIcon: boolean = false;
    showLoading: boolean = false;

    constructor(public props: PromptDefinition & SendUpdateToCollector) {
        super(props);

        this.debounced = debounceFunction((response: string) => {
            this.changeResponse(response);
            this.showLoading = false;
        }, 2000);

        this.promptType = PromptMapping.get(this.props.inputType) ?? TextPrompt;
    }

    changeResponse(text: string) {
        // put some "send to backend" code here
        this.props.sendUpdateToCollector(text);
        console.log(`response for "${this.props.idKey}" changed to "${text}"`);
    }

    doStuff(event: React.ChangeEvent<HTMLInputElement>) {
        this.showIcon = true;
        this.showLoading = true;
        this.debounced(event.target.value);
    }

    render() {
        return (
            <div className="w-full max-w-md px-4">
                <Field>
                    <Label className="text-sm/6 font-medium text-black">
                        {this.props.displayText}
                    </Label>
                    {this.promptType({
                        doSomething: this.doStuff,
                        idKey: this.props.idKey,
                        choices: this.props.choices,
                    })}
                    <LoadingIcon
                        key={this.showIcon}
                        showIcon={this.showIcon}
                        showLoading={this.showLoading}
                    />
                </Field>
            </div>
        );
    }
}
