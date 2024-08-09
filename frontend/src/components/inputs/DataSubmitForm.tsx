import { useForm } from "@mantine/form";
import { Button, Fieldset, Grid, Group } from "@mantine/core";
import { ReactNode, useState } from "react";
import { PromptDefinition } from "../../models/PromptTypes";
import { Answers } from "../../models/Answers";
import { FailAlert, SuccessAlert } from "../output/alert/AlertOnSubmission";
import { handleFormSubmission } from "../output/FormSubmissionHandler";

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

type PromptCollectorProps = { promptDefinitions: PromptDefinition[] };

export enum Status {
    PENDING = "pending",
    SUCCESS = "success",
    ERROR = "error",
}

export function DataSubmitForm(props: PromptCollectorProps) {
    let formValues: Answers = { answers: [] };
    let children: ReactNode[] = [];
    const [open, setOpen] = useState<Status>(Status.PENDING);

    const form = useForm({
        mode: "uncontrolled",
        initialValues: {} as Answers,
        onValuesChange(values, previous) {
            if (
                Object.keys(previous).length != 0 &&
                Object.keys(values).length != 0
            ) {
                const changedValues = values.answers.filter(
                    (answer) => answer.value != "",
                );

                updateImageBlur(
                    "gatitoImage",
                    Object.keys(previous.answers).length - changedValues.length,
                );
            }
        },
    });

    props.promptDefinitions.forEach((promptDef: PromptDefinition) => {
        formValues.answers.push({ type: promptDef.idKey, value: "" });
        const index = formValues.answers.length - 1;

        //this could be curried but it would serve no purpose in this case)
        const validateAndUpdateForm = (value: any) => {
            form.setFieldValue(`answers.${index}.value`, value);
        };

        const { inputType, ...childProps } = promptDef;
        children.push(
            promptDef.inputType({
                idKey: childProps.idKey,
                labelText: childProps.displayText,
                choices: childProps.choices,
                validateAndUpdate: validateAndUpdateForm,
                form: form,
                max: childProps.max,
            }),
        );
    });

    form.initialize(formValues);

    return (
        <Grid columns={2} align="center" justify="center">
            <Grid.Col span={1} pl={100} pr={100} pt={20} pb={20}>
                <Fieldset radius="lg" variant="filled">
                    {...children}
                    <Group justify="flex-end" mt="md">
                        {open == Status.SUCCESS && (
                            <SuccessAlert setState={setOpen}></SuccessAlert>
                        )}
                        {open == Status.ERROR && (
                            <FailAlert setState={setOpen}></FailAlert>
                        )}
                        <Button
                            onClick={() => {
                                handleFormSubmission(form.getValues())
                                    .then((_) => setOpen(Status.SUCCESS))
                                    .catch((error) => {
                                        console.error(error);
                                        setOpen(Status.ERROR);
                                    });
                            }}
                        >
                            Submit
                        </Button>
                    </Group>
                </Fieldset>
            </Grid.Col>
            <Grid.Col span={1}>
                <img
                    className="blur-3xl"
                    id="gatitoImage" //THE BOY
                    src="gatito.jpg"
                />
            </Grid.Col>
        </Grid>
    );
}
