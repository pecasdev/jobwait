import { DatePickerInput } from "@mantine/dates";
import { PromptTypeProps } from "../PromptTypes";
import { useState } from "react";

export default function DatePickerPrompt(props: PromptTypeProps) {
    const [value, setValue] = useState<Date | null>(null);
    return (
        <DatePickerInput
            label={props.labelText}
            placeholder="Pick date"
            value={value}
            onChange={(val) => {
                setValue(val);
                props.validateAndUpdate(val);
            }}
        />
    );
}
