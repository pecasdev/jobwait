import * as React from "react";
import "./App.css";
import GraphExample from "./GraphExample";
import PromptCollector from "./reusable/PromptCollector";
import { PromptDefinition } from "./reusable/PromptTypes";
import { Chart, registerables } from "chart.js";
import SelectMenu from "./reusable/SelectMenu";

Chart.register(...registerables);

export default function App() {
    const promptDefinition: PromptDefinition[] = [
        {
            displayText: "Are you currently employed?",
            idKey: "employment_status",
            inputType: "text",
        },
        {
            displayText: "When did you accept your position?",
            idKey: "date_of_acceptance",
            inputType: "slider",
        },
        {
            displayText: "When did you start applying for jobs?",
            idKey: "date_of_app_start",
            inputType: "slider",
        },
        {
            displayText: "What choice best describes your work model?",
            idKey: "work_model",
            inputType: "slider",
        },
        {
            displayText: "What choice best describes your work contract?",
            idKey: "work_contract",
            inputType: "radio",
        },
        {
            displayText:
                "How many job applications (approximately) did you send out before you got your job?",
            idKey: "num_job_apps",
            inputType: "radio",
        },
        {
            displayText: "What is your job title?",
            idKey: "job_title",
            inputType: "radio",
        },
        {
            displayText:
                "How many years of professional experience in your field did you have prior to accepting your job offer?",
            idKey: "num_years_exp",
            inputType: "radio",
        },
        {
            displayText:
                "What is the highest level of education you have achieved?",
            idKey: "highest_education",
            inputType: "radio",
        },
    ];

    return (
        <div>
            <h1>job! wait...</h1>
            <PromptCollector
                promptDefinitions={promptDefinition}
            ></PromptCollector>
            <GraphExample></GraphExample>
        </div>
    );
}
