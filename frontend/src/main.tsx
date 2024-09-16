import { localStorageColorSchemeManager, MantineProvider } from "@mantine/core";
import { Chart, registerables } from "chart.js";
import ChartDataLabels from "chartjs-plugin-datalabels";
import React from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Root from "./routes/root";
import ErrorPage from "./routes/error-page";
import { LinkedInCallback } from "react-linkedin-login-oauth2";

const colorSchemeManager = localStorageColorSchemeManager({
    key: "mantine-color-scheme-value",
});

Chart.register(ChartDataLabels);
Chart.register(...registerables);

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <ErrorPage />,
    },
    {
        path: "/auth/callback",
        element: <LinkedInCallback />,
    },
]);

const container = document.getElementById("root");
const root = createRoot(container!);

root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>,
);
