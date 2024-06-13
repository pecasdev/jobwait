import * as React from "react";
import Prompt from "./Prompt";
import { PromptDefinition, PromptResponse } from "./PromptTypes";
import { Button, Listbox } from "@headlessui/react";
import ListBoxPrompt from "./inputs/ListBoxPrompt";

type PromptCollectorProps = { promptDefinitions: PromptDefinition[] };

export default class PromptCollector extends React.Component {
    promptResponses: Map<string, PromptResponse> = new Map();
    constructor(public props: PromptCollectorProps) {
        super(props);
    }

    private updateImageBlur() {
        const blurQuantity =
            this.props.promptDefinitions.length - this.promptResponses.size;

        const blurScaling = 5;

        const image = document.getElementById("gatitoImage");
        if (!image) {
            console.error("CANNOT FIND GATITO!!! :(((");
        } else {
            image.style.filter = `blur(${blurQuantity * blurScaling}px)`;
        }
    }

    private initSendUpdate(idKey: string) {
        return (response: PromptResponse) => {
            this.promptResponses.set(idKey, response);
            this.updateImageBlur();
        };
    }

    private submitResponses() {
        console.log("sending this stuff to the backend:", this.promptResponses);
        // todo - actually do it
    }

    componentDidMount(): void {
        this.updateImageBlur();
    }

    render(): React.ReactNode {
        return (
            <div className="border border-black">
                <p className="text-2xl">super cool form you should fill out</p>
                <div className="flex flex-row gap-x-40">
                    <tbody className="space-y-6">
                        {this.props.promptDefinitions.map(
                            (promptDef, index) => (
                                <Prompt
                                    key={index}
                                    displayText={promptDef.displayText}
                                    idKey={promptDef.idKey}
                                    inputType={promptDef.inputType}
                                    sendUpdateToCollector={this.initSendUpdate(
                                        promptDef.idKey,
                                    )}
                                />
                            ),
                        )}
                        <Button
                            onClick={this.submitResponses.bind(this)}
                            className="gap-2 rounded-md bg-gray-700 py-1.5 
                            px-3 text-sm/6 font-semibold text-white shadow-inner shadow-white/10 
                            focus:outline-none 
                            data-[hover]:bg-gray-600 
                            data-[open]:bg-gray-700 
                            data-[focus]:outline-1 
                            data-[focus]:outline-white"
                        >
                            Submit
                        </Button>
                        {/* <ListBoxPrompt
                            idKey="123"
                            choices={
                                this.props.promptDefinitions[0].choices ?? [
                                    "t",
                                    "d",
                                    "a",
                                ]
                            }
                        ></ListBoxPrompt> */}
                    </tbody>
                    <img id="gatitoImage" src="gatito.jpg" />
                </div>
            </div>
        );
    }
}
