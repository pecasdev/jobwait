import { useState } from "react";
import { PromptTypeProps } from "../../model/PromptTypes";
import { Slider, Text } from "@mantine/core";

export default function SliderPrompt(props: PromptTypeProps) {
    const [value, setValue] = useState(0);
    const [_, setEndValue] = useState(0);

    return (
        <>
            <Text fw={500} size="sm">
                {props.labelText}
            </Text>
            <Slider
                value={value}
                onChange={setValue}
                onChangeEnd={(val) => {
                    setEndValue(val);
                    props.validateAndUpdate(val);
                }}
                max={props.max}
            />
        </>
    );
}
