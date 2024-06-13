import { PromptDefinition, SendUpdateToCollector } from "../PromptTypes";
import ListBoxPrompt from "./ListBoxPrompt";
import TextPrompt from "./TextPrompt";

export const PromptMapping: Map<
    string,
    (props: PromptDefinition & SendUpdateToCollector) => React.ReactNode
> = new Map([
    ["text", TextPrompt],
    ["listbox", ListBoxPrompt],

    // "button" : ,

    // "submit" : ,
    // "checkbox" : ,
    // "number" : ,
    // "date" : ,
    // "radio" : ,
    // "range" : ,
    // "datetime-local" : ,
    // "email" : ,
    // "file" : ,
    // "hidden" : ,
    // "image" : ,
    // "month" : ,
    // "password" : ,
    // "search" : ,
    // "reset" : ,
    // "color" : ,
    // "tel"
    // "time"
    // "url"
    // "week"
    // (string & {});
]);
