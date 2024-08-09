import "./index.css";
import App from "./App";
import { localStorageColorSchemeManager, MantineProvider } from "@mantine/core";
import React from "react";

const colorSchemeManager = localStorageColorSchemeManager({
    key: "mantine-color-scheme-value",
});

import { createRoot } from "react-dom/client";
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
