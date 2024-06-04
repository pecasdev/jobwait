import * as React from "react";
import { useState } from "react";
import "./App.css";
import PromptCollector, { PromptDefinition } from "./reusable/PromptCollector";

async function queryAddress(address: string): Promise<string> {
    if (address) {
        const response = await fetch(address, {
            method: "GET",
            headers: { "Content-Type": "application/text" },
        });
        return response.text();
    }

    return Promise.reject("address not provided");
}

// why is typescript so limited, this should be a default function
function formDataToEntries(formData: FormData): { [key: string]: any } {
    const stuff: { [key: string]: any } = {};
    formData.forEach((value, key) => (stuff[key] = value));
    return stuff;
}

function handleSubmit(e: React.FormEvent, setResponse: (_: string) => void) {
    // Prevent the browser from reloading the page
    e.preventDefault();

    // Read the form data
    const form: HTMLFormElement = e.target as HTMLFormElement; // remove "as"
    const formData = new FormData(form);

    let formJson = formDataToEntries(formData);
    queryAddress(formJson["queryInput"]).then((response) =>
        setResponse(response ?? "some kind of error"),
    );

    // // You can pass formData as a fetch body directly:
    // fetch('/some-api', { method: form.method, body: formData });
}

export default function App() {
    const [response, setResponse] = useState("");

    const promptDefinition: PromptDefinition[] = [
        {
            displayText: "did you get accepted by the milk company?",
            idKey: "milk_acceptance",
            inputType: "text"
        },
        {
            displayText: "how many job applications have you sent out?",
            idKey: "job_application_count",
            inputType: "slider"
        }
    ];

    return (
        <div>
            <h1>Professional Environment.</h1>
            <form onSubmit={(e) => handleSubmit(e, setResponse)}>
                <input name="queryInput" id="queryInput" required={true} />
                <button type="submit">Search</button>
            </form>
            <h2>{response}</h2>

            <PromptCollector
                promptDefinitions={promptDefinition}
            ></PromptCollector>
        </div>
    );
}
