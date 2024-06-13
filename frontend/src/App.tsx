import * as React from "react";
import "./App.css";
import GraphExample from "./GraphExample";
import PromptCollector from "./reusable/PromptCollector";
import { Chart, registerables } from "chart.js";
import { defaultPromptDefinitions } from "./reusable/default/DefaultPromptDefinitions";

Chart.register(...registerables);

export default function App() {
    return (
        <div>
            <h1>job! wait...</h1>
            <PromptCollector
                promptDefinitions={defaultPromptDefinitions}
            ></PromptCollector>
            <GraphExample></GraphExample>
        </div>
    );
}
