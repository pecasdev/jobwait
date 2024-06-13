import * as React from "react";
import { PromptTypeProps } from "../PromptTypes";
import { Input } from "@headlessui/react";

export default function TextPrompt(props: PromptTypeProps) {
    return (
        <Input
            className={`mt-3 block w-full rounded-lg border-none bg-black/5 py-1.5 px-3 text-sm/6 text-black
            focus:outline-none data-[focus]:outline-2 data-[focus]:-outline-offset-2 data-[focus]:outline-black/25`}
            name="displayText"
            onBlur={(e: React.FocusEvent<HTMLInputElement, Element>) =>
                props.validateAndUpdate(e.target.value)
            }
        />
    );
}
