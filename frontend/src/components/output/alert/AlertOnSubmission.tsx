import { Alert } from "@mantine/core";
import { IconCheck, IconExclamationCircle } from "@tabler/icons-react";
import classes from "./AlertOnSubmission.module.css";
import { Status } from "../../input/DataSubmitForm";

export function FailAlert(props: { setState: (state: Status) => void }) {
    const failIcon = <IconExclamationCircle />;
    return (
        <Alert
            variant="filled"
            color="red"
            radius="xl"
            title="Submission failed! Please try again!"
            withCloseButton
            classNames={{ root: classes.root }}
            onClose={() => props.setState(Status.PENDING)}
            icon={failIcon}
        ></Alert>
    );
}

export function SuccessAlert(props: { setState: (state: Status) => void }) {
    const successIcon = <IconCheck />;
    return (
        <Alert
            variant="filled"
            color="green"
            radius="xl"
            title="Success! Thank you for sharing!"
            withCloseButton
            classNames={{ root: classes.root }}
            onClose={() => props.setState(Status.PENDING)}
            icon={successIcon}
        ></Alert>
    );
}
