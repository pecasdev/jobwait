import { DataSubmitForm } from "../reusable/DataSubmitForm";
import { defaultPromptDefinitions } from "../reusable/default/DefaultPromptDefinitions";

export function SubmitRoute() {
    return (
        <DataSubmitForm
            promptDefinitions={defaultPromptDefinitions}
        ></DataSubmitForm>
    );
}
