import { PromptDefinition } from "../PromptTypes";
import { ComboboxPrompt } from "../inputs/ComboboxPrompt";
import DatePickerPrompt from "../inputs/DatePickerPrompt";
import SliderPrompt from "../inputs/SliderPrompt";
import TextPrompt from "../inputs/TextPrompt";

export const defaultPromptDefinitions: PromptDefinition[] = [
    {
        displayText: "When did you accept your position?",
        idKey: "jobacceptdate",
        inputType: DatePickerPrompt,
    },
    {
        displayText: "When did you start applying for jobs?",
        idKey: "jobsearchstartdate",
        inputType: DatePickerPrompt,
    },
    {
        displayText: "What choice best describes your work model?",
        idKey: "workmodel",
        inputType: ComboboxPrompt,
        choices: ["On-site", "Hybrid", "Remote"],
    },
    {
        displayText: "What choice best describes your work contract?",
        idKey: "workcontract",
        inputType: ComboboxPrompt,
        choices: ["Full-time", "Part-time", "Contract", "Internship", "Other"],
    },
    {
        displayText:
            "How many job applications (approximately) did you send out before you got your job?",
        idKey: "jobapplicationcount",
        max: 10000,
        inputType: SliderPrompt,
    },
    {
        displayText: "What is your job title?",
        idKey: "jobtitle",
        inputType: TextPrompt,
    },
    {
        displayText:
            "How many years of professional experience in your field did you have prior to accepting your job offer?",
        idKey: "yearsofproexperience",
        max: 50,
        inputType: SliderPrompt,
    },
    {
        displayText:
            "What is the highest level of education you have achieved?",
        idKey: "educationlevel",
        choices: ["High School", "College", "University", "Other"],
        inputType: ComboboxPrompt,
    },
];
