import { BasicForm } from "../reusable/BasicForm";
import { defaultPromptDefinitions } from "../reusable/default/DefaultPromptDefinitions";

export function SubmitRoute() {
    return <BasicForm promptDefinitions={defaultPromptDefinitions}></BasicForm>;
}
