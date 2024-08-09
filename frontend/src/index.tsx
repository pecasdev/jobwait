import * as React from "react";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.min.css";
import { createRoot } from "react-dom/client";
import { localStorageColorSchemeManager, MantineProvider } from "@mantine/core";

const rootElement = document.getElementById("root");

const colorSchemeManager = localStorageColorSchemeManager({
    key: "mantine-color-scheme-value",
});

if (rootElement) {
    const root = createRoot(rootElement);
    root.render(
        <MantineProvider
            defaultColorScheme="dark"
            colorSchemeManager={colorSchemeManager}
        >
            <React.StrictMode>
                <App />
            </React.StrictMode>
            ,
        </MantineProvider>,
    );
}
