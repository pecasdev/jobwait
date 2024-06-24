// Import styles of packages that you've installed.
// All packages except `@mantine/hooks` require styles imports
import "@mantine/core/styles.css";

import "./App.css";
import PromptCollector from "./reusable/PromptCollector";
import { Chart, registerables } from "chart.js";
import { defaultPromptDefinitions } from "./reusable/default/DefaultPromptDefinitions";
import { MantineProvider } from "@mantine/core";

Chart.register(...registerables);

export default function App() {
    return (
        <MantineProvider>
            <div>
                <h1>job! wait...</h1>
                <PromptCollector
                    promptDefinitions={defaultPromptDefinitions}
                ></PromptCollector>
            </div>
        </MantineProvider>
    );
}
