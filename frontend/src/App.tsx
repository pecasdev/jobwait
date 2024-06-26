import "./App.css";
import { Chart, registerables } from "chart.js";
import { defaultPromptDefinitions } from "./reusable/default/DefaultPromptDefinitions";
import { BasicForm } from "./reusable/BasicForm";

Chart.register(...registerables);

export default function App() {
    return (
        <div>
            <h1>job! wait...</h1>
            <BasicForm promptDefinitions={defaultPromptDefinitions}></BasicForm>
        </div>
    );
}
