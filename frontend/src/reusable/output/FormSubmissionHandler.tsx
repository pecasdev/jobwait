import { Answers } from "../../models/Answers";


export async function handleFormSubmission(formValues: Answers) {
    console.log(formValues);

    const response = await fetch(new URL("http://localhost:9000/answers/submit?at=valid123"), {
      method: 'POST',
      body: JSON.stringify(formValues),
      headers: {'Content-Type': 'application/json'}  
    })

    console.log(response.status);
    response.text().then((value) => console.log(value));
}
