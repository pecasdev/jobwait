import * as React from "react";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.min.css";
import { createRoot } from "react-dom/client";
import { ApolloClient, InMemoryCache, ApolloProvider } from "@apollo/client";
import { MantineProvider } from "@mantine/core";

const client = new ApolloClient({
    uri: "http://localhost:9000/graphql",
    cache: new InMemoryCache(),
});

const rootElement = document.getElementById("root");

if (rootElement) {
    const root = createRoot(rootElement);
    root.render(
        <React.StrictMode>
            <ApolloProvider client={client}>
                <MantineProvider
                    defaultColorScheme="light"
                    forceColorScheme="light"
                >
                    <App />
                </MantineProvider>
            </ApolloProvider>
        </React.StrictMode>,
    );
}
