import { Answers } from "../../models/Answers";
import { SubmissionError } from "./SubmissionError";

export async function handleFormSubmission(
    formValues: Answers,
): Promise<string | Error> {
    const response = await fetch(
        new URL("http://localhost:9000/answers/submit?at=valid123"),
        {
            method: "POST",
            body: JSON.stringify(formValues),
            headers: { "Content-Type": "application/json" },
        },
    );

    const responseStatusCode = response.status;
    const responseValue = await response.text();
    if (responseStatusCode == 200) {
        return responseValue;
    } else {
        throw new SubmissionError(responseValue);
    }
}
