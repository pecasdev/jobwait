import * as React from "react";
import { useState } from "react";
import { PromptDefinition } from "./PromptCollector";
import debounceFunction from "../util/debounceFunction";
import { LoadingIcon } from "./LoadingIcon";

type PromptState = { showIcon: boolean; showLoading: boolean };

export default class Prompt extends React.Component<
    PromptDefinition,
    PromptState
> {
    debounced: (...args: any[]) => void;

    constructor(private promptDefinition: PromptDefinition) {
        super(promptDefinition);

        this.state = {
            showIcon: false,
            showLoading: false,
        };
        
        this.debounced = debounceFunction((response: string) => {
            this.changeResponse(response);
            this.setState({ showLoading: false });
        }, 2000);
    }

    changeResponse(text: String) {
        // put some "send to backend" code here
        console.log(
            `response for "${this.promptDefinition.idKey}" changed to "${text}"`,
        );
    }

    doStuff(event: React.ChangeEvent<HTMLInputElement>) {
        this.setState({
            showIcon: true,
            showLoading: true,
        });
        this.debounced(event.target.value);
    }

    render() {
        return (
            <div>
                <label className="flex flex-col">
                    {this.promptDefinition.displayText}

                    <div className="flex flex-row">
                        <input
                            className="border border-black border-4"
                            id={this.promptDefinition.idKey}
                            name="displayText"
                            onChange={this.doStuff.bind(this)}
                        />
                        <LoadingIcon
                            key={this.state.showIcon}
                            showIcon={this.state.showIcon}
                            showLoading={this.state.showLoading}
                        />
                    </div>
                </label>
            </div>
        );
    }
}
