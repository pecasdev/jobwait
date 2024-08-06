import * as React from "react";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.min.css";
import { createRoot } from "react-dom/client";
import { MantineProvider } from "@mantine/core";

const rootElement = document.getElementById("root");

if (rootElement) {
    const root = createRoot(rootElement);
    root.render(
        <React.StrictMode>
            <MantineProvider
                defaultColorScheme="light"
                forceColorScheme="light"
            >
                <App />
            </MantineProvider>
        </React.StrictMode>,
    );
}
