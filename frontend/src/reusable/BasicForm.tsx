import { useForm } from "@mantine/form";
import { Button, Fieldset, Group } from "@mantine/core";
import { PromptDefinition } from "./PromptTypes";
import { ReactNode } from "react";

type PromptCollectorProps = { promptDefinitions: PromptDefinition[] };

function updateImageBlur(
    imageName: string,
    blurQuantity: number = 9,
    blurScaling: number = 7,
) {
    const image = document.getElementById(imageName);
    if (!image) {
        console.error(`Cannot find image with name ${imageName}`);
    } else {
        image?.classList.remove("blur-3xl");
        image.style.filter = `blur(${blurQuantity * blurScaling}px)`;
    }
}

type FormValues = {
    prompts: {
        [key: string]: {
            data: string;
        };
    };
};

export function BasicForm(props: PromptCollectorProps) {
    let formValues: Map<string, any> = new Map<string, any>();

    let children: ReactNode[] = [];

    const form = useForm({
        mode: "uncontrolled",
        initialValues: {} as FormValues,
        onValuesChange(values, previous) {
            if (
                Object.keys(previous).length != 0 &&
                Object.keys(values).length != 0
            ) {
                const changedValues = [...Object.values(values.prompts)].filter(
                    (value) => value.data != "",
                );

                updateImageBlur(
                    "gatitoImage",
                    Object.keys(previous.prompts).length - changedValues.length,
                );
            }
        },
    });

    props.promptDefinitions.forEach((promptDef: PromptDefinition) => {
        formValues.set(promptDef.idKey, { data: "" });
        const { inputType, ...childProps } = promptDef;
        children.push(
            promptDef.inputType({
                idKey: childProps.idKey,
                labelText: childProps.displayText,
                choices: childProps.choices,
                form: form,
                max: childProps.max,
            }),
        );
    });

    const formSet = { prompts: Object.fromEntries(formValues) };

    form.setInitialValues(formSet);
    form.initialize(formSet);

    return (
        <div className="grid grid-cols-2 gap-x-52">
            <div>
                <Fieldset
                    legend="Give us your data!"
                    radius="md"
                    variant="filled"
                >
                    {...children}
                    <Group justify="flex-end" mt="md">
                        <Button>Submit</Button>
                    </Group>
                </Fieldset>
            </div>
            <div>
                <img
                    className="obtain-content blur-3xl"
                    id="gatitoImage" //THE BOY
                    src="gatito.jpg"
                />
            </div>
        </div>
    );
}
