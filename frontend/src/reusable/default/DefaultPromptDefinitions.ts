import { PromptDefinition } from "../PromptTypes";

export const defaultPromptDefinitions: PromptDefinition[] = [
    {
        displayText: "Are you currently employed?",
        idKey: "employment_status",
        inputType: "text",
    },
    {
        displayText: "When did you accept your position?",
        idKey: "date_of_acceptance",
        inputType: "datetime",
    },
    {
        displayText: "When did you start applying for jobs?",
        idKey: "date_of_app_start",
        inputType: "datetime",
    },
    {
        displayText: "What choice best describes your work model?",
        idKey: "work_model",
        inputType: "listbox",
        choices: ["on-site", "hybrid", "remote"],
    },
    {
        displayText: "What choice best describes your work contract?",
        idKey: "work_contract",
        inputType: "listbox",
        choices: ["full-time", "part-time", "contract", "internship", "other"],
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
