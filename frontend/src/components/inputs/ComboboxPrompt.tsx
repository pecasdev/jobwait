import { useState } from "react";
import { PromptTypeProps } from "../../models/PromptTypes";
import {
    CheckIcon,
    Combobox,
    Group,
    InputBase,
    useCombobox,
} from "@mantine/core";

export function ComboboxPrompt(props: PromptTypeProps) {
    let comboboxStore = useCombobox({
        onDropdownClose: () => comboboxStore.resetSelectedOption(),
    });

    const [value, setValue] = useState<string | null>("");

    const options = props.choices?.map((choice: string) => (
        <Combobox.Option key={choice} value={choice} active={value == choice}>
            <Group gap="sm">
                {value == choice ? <CheckIcon size={12} /> : null}
                <span>{choice}</span>
            </Group>
        </Combobox.Option>
    ));

    return (
        <Combobox
            resetSelectionOnOptionHover
            onOptionSubmit={(val) => {
                props.validateAndUpdate(val);
                setValue(val);
                comboboxStore.updateSelectedOptionIndex("active");
                comboboxStore.closeDropdown();
            }}
            store={comboboxStore}
        >
            <Combobox.Target>
                <InputBase
                    label={props.labelText}
                    component="button"
                    type="button"
                    pointer
                    rightSection={<Combobox.Chevron />}
                    rightSectionPointerEvents="none"
                    onClick={() => comboboxStore.toggleDropdown()}
                >
                    {value}
                </InputBase>
            </Combobox.Target>

            <Combobox.Dropdown>
                <Combobox.Options>{options}</Combobox.Options>
            </Combobox.Dropdown>
        </Combobox>
    );
}
