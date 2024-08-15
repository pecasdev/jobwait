import { localStorageColorSchemeManager, MantineProvider } from "@mantine/core";
import App from "../App";

const colorSchemeManager = localStorageColorSchemeManager({
    key: "mantine-color-scheme-value",
});

export default function Root() {
    return (
        <MantineProvider
            defaultColorScheme="dark"
            colorSchemeManager={colorSchemeManager}
        >
            <App />
        </MantineProvider>
    );
}
