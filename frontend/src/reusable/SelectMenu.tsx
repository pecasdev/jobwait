import * as React from "react";
import { useState } from "react";
import { useQuery } from "@apollo/client";
import { ALL_USERS } from "../documents/UserDocument";
import { Field, Label, Select } from "@headlessui/react";
import { ChevronDownIcon } from "@heroicons/react/20/solid";

//Add some data or fetcher here to populate the menu for generic dropdown menu action?
export default function SelectMenu() {
    const [selected, setSelected] = useState("");

    const { data, loading, error } = useQuery(ALL_USERS);

    if (error) {
        throw error;
    }

    return (
        <div className="w-full max-w-md px-4">
            <Field>
                <Label className="text-sm/6 font-medium text-black">
                    User Query Example
                </Label>
                <div className="relative">
                    <Select
                        className={`mt-3 block w-full appearance-none rounded-lg border-none bg-black/5 py-1.5 px-3 text-sm/6 text-black
                            focus:outline-none data-[focus]:outline-2 data-[focus]:-outline-offset-2 data-[focus]:outline-black/25
                            *:text-black`}
                    >
                        {data?.getAllUsers.map((user) => (
                            <option value={user?.userID}>{user?.userID}</option>
                        ))}
                    </Select>
                    <ChevronDownIcon
                        className="group pointer-events-none absolute top-2.5 right-2.5 size-4 fill-black/60"
                        aria-hidden="true"
                    />
                </div>
            </Field>
        </div>
    );
}
