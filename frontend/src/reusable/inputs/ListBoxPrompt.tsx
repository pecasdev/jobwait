import * as React from "react";
import { SimplePromptDefinition } from "../PromptTypes";
import {
    Listbox,
    ListboxButton,
    ListboxOption,
    ListboxOptions,
    Transition,
} from "@headlessui/react";
import { ChevronDownIcon, CheckIcon } from "@heroicons/react/20/solid";

export default function ListBoxPrompt(props: SimplePromptDefinition) {
    let currentSelected = props.state.selected;
    return (
        <Listbox
            onChange={(e) => {
                props.stateManager(e);
                if (props.doSomething) props.doSomething(e);
            }}
            value={currentSelected}
        >
            <ListboxButton
                className={`relative block w-full rounded-lg bg-black/5 py-1.5 pr-8 pl-3 text-left text-sm/6 text-black
                    focus:outline-none data-[focus]:outline-2 data-[focus]:-outline-offset-2 data-[focus]:outline-black/25`}
            >
                {currentSelected}
                <ChevronDownIcon
                    className="group pointer-events-none absolute top-2.5 right-2.5 size-4 fill-black/60"
                    aria-hidden="true"
                />
            </ListboxButton>
            <Transition
                leave="transition ease-in duration-100"
                leaveFrom="opacity-100"
                leaveTo="opacity-0"
            >
                <ListboxOptions
                    anchor="bottom"
                    className="w-[var(--button-width)] rounded-xl border border-black/5 bg-gray-100 p-1 [--anchor-gap:var(--spacing-1)] focus:outline-none"
                >
                    {props.choices?.map((choice) => (
                        <ListboxOption
                            key={choice}
                            value={choice}
                            className="group flex cursor-default items-center gap-2 rounded-lg py-1.5 px-3 select-none data-[focus]:bg-black/10"
                        >
                            <CheckIcon className="invisible size-4 fill-black group-data-[selected]:visible" />
                            <div className="text-sm/6 text-black">{choice}</div>
                        </ListboxOption>
                    ))}
                </ListboxOptions>
            </Transition>
        </Listbox>
    );
}
