import { DatePickerInput } from "@mantine/dates";
import { PromptTypeProps } from "../../model/PromptTypes";
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

                if (val == null) {
                    props.validateAndUpdate(null);
                } else {
                    props.validateAndUpdate(val.toISOString().split("T")[0]);
                }
            }}
        />
    );
}
