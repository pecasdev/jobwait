import * as React from "react";
import debounceFunction from "../util/debounceFunction";
import { LoadingIcon } from "./LoadingIcon";
import { PromptDefinition, SendUpdateToCollector } from "./PromptTypes";

type PromptState = { showIcon: boolean; showLoading: boolean };

export default class Prompt extends React.Component<
    PromptDefinition & SendUpdateToCollector,
    PromptState
> {
    debounced: (...args: any[]) => void;

    constructor(public props: PromptDefinition & SendUpdateToCollector) {
        super(props);

        this.state = {
            showIcon: false,
            showLoading: false,
        };

        this.debounced = debounceFunction((response: string) => {
            this.changeResponse(response);
            this.setState({ showLoading: false });
        }, 2000);
    }

    changeResponse(text: string) {
        // put some "send to backend" code here
        this.props.sendUpdateToCollector(text)
        console.log(
            `response for "${this.props.idKey}" changed to "${text}"`,
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
                    {this.props.displayText}

                    <div className="flex flex-row">
                        <input
                            className="border border-black border-4"
                            id={this.props.idKey}
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
