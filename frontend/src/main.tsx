import "./index.css";
import App from "./App";
import { localStorageColorSchemeManager, MantineProvider } from "@mantine/core";
import React from "react";
import { createRoot } from "react-dom/client";
import { Chart, registerables } from "chart.js";
import ChartDataLabels from 'chartjs-plugin-datalabels';

const colorSchemeManager = localStorageColorSchemeManager({
    key: "mantine-color-scheme-value",
});

Chart.register(ChartDataLabels);
Chart.register(...registerables);

const container = document.getElementById("root");
const root = createRoot(container!);

root.render(
    <React.StrictMode>
        <MantineProvider
            defaultColorScheme="dark"
            colorSchemeManager={colorSchemeManager}
        >
            <App />
        </MantineProvider>
    </React.StrictMode>,
);
