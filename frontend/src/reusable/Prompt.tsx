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

type PromptState = {
    showIcon: boolean;
    showLoading: boolean;
    selected: string;
};

export default class Prompt extends React.Component<
    PromptDefinition & SendUpdateToCollector,
    PromptState
> {
    debounced: (...args: any[]) => void;
    promptType: (props: SimplePromptDefinition) => React.ReactNode;

    constructor(public props: PromptDefinition & SendUpdateToCollector) {
        super(props);

        this.state = {
            showIcon: false,
            showLoading: false,
            selected: this.props.choices ? this.props.choices[0] : "",
        };

        this.debounced = debounceFunction((response: string) => {
            this.changeResponse(response);
            this.setState({ showLoading: false });
        }, 2000);

        this.promptType = PromptMapping.get(this.props.inputType) ?? TextPrompt;
        this.doStuff = this.doStuff.bind(this);
        this.setSelected = this.setSelected.bind(this);
    }

    changeResponse(text: string) {
        // put some "send to backend" code here
        this.props.sendUpdateToCollector(text);
        console.log(`response for "${this.props.idKey}" changed to "${text}"`);
    }

    doStuff(response: string) {
        this.setState({
            showIcon: true,
            showLoading: true,
        });
        this.debounced(response);
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
                        doSomething: this.doStuff,
                        idKey: this.props.idKey,
                        choices: this.props.choices,
                        state: this.state,
                        stateManager: this.setSelected,
                    })}
                    <LoadingIcon
                        key={this.state.showIcon}
                        showIcon={this.state.showIcon}
                        showLoading={this.state.showLoading}
                    />
                </Field>
            </div>
        );
    }
}
