import { PromptDefinition } from "../PromptTypes";
import { ComboboxPrompt } from "../inputs/ComboboxPrompt";
import DatePickerPrompt from "../inputs/DatePickerPrompt";
import SliderPrompt from "../inputs/SliderPrompt";
import TextPrompt from "../inputs/TextPrompt";

export const defaultPromptDefinitions: PromptDefinition[] = [
    {
        displayText: "Are you currently employed?",
        idKey: "employment_status",
        choices: ["Yes", "No"],
        inputType: ComboboxPrompt,
    },
    {
        displayText: "When did you accept your position?",
        idKey: "date_of_acceptance",
        inputType: DatePickerPrompt,
    },
    {
        displayText: "When did you start applying for jobs?",
        idKey: "date_of_app_start",
        inputType: DatePickerPrompt,
    },
    {
        displayText: "What choice best describes your work model?",
        idKey: "work_model",
        inputType: ComboboxPrompt,
        choices: ["On-site", "Hybrid", "Remote"],
    },
    {
        displayText: "What choice best describes your work contract?",
        idKey: "work_contract",
        inputType: ComboboxPrompt,
        choices: ["Full-time", "Part-time", "Contract", "Internship", "Other"],
    },
    {
        displayText:
            "How many job applications (approximately) did you send out before you got your job?",
        idKey: "num_job_apps",
        max: 1000,
        inputType: SliderPrompt,
    },
    {
        displayText: "What is your job title?",
        idKey: "job_title",
        inputType: TextPrompt,
    },
    {
        displayText:
            "How many years of professional experience in your field did you have prior to accepting your job offer?",
        idKey: "num_years_exp",
        max: 50,
        inputType: SliderPrompt,
    },
    {
        displayText:
            "What is the highest level of education you have achieved?",
        idKey: "highest_education",
        choices: ["High School", "College", "University", "Other"],
        inputType: ComboboxPrompt,
    },
];
