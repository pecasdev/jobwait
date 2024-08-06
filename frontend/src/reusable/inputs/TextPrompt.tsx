import { PromptTypeProps } from "../PromptTypes";
import { TextInput } from "@mantine/core";

export default function TextPrompt(props: PromptTypeProps) {
    return (
        <TextInput
            label={props.labelText}
            name="displayText"
            onBlur={(val) => {
                if (val.target.value.trim())
                    props.validateAndUpdate(val.target.value.trim());
            }}
        />
    );
}
