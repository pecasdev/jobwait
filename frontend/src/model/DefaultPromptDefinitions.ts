import { PromptDefinition } from "./PromptTypes";
import { ComboboxPrompt } from "../components/input/ComboboxPrompt";
import DatePickerPrompt from "../components/input/DatePickerPrompt";
import SliderPrompt from "../components/input/SliderPrompt";
import TextPrompt from "../components/input/TextPrompt";

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
        choices: ["ON_SITE", "HYBRID", "REMOTE"],
    },
    {
        displayText: "What choice best describes your work contract?",
        idKey: "workcontract",
        inputType: ComboboxPrompt,
        choices: ["FULL_TIME", "PART_TIME", "CONTRACT", "INTERNSHIP", "OTHER"],
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
        choices: [
            "LESS_THAN_HIGHSCHOOL",
            "HIGHSCHOOL_DIPLOMA",
            "ASSOCIATE_DEGREE",
            "BACHELOR_DEGREE",
            "MASTER_DEGREE",
            "DOCTORAL_DEGREE",
            "OTHER",
        ],
        inputType: ComboboxPrompt,
    },
];
