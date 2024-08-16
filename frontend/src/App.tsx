import { Header } from "./components/header/Header";
import { Box, Flex, Paper } from "@mantine/core";
import { defaultPromptDefinitions } from "./models/DefaultPromptDefinitions";

import classes from "./App.module.css";
import { DataSubmitForm } from "./components/inputs/DataSubmitForm";
import { StatsComponent } from "./components/stats/StatsComponent";
import { Footer } from "./components/footer/Footer";

export default function App() {
    return (
        <Box ml={125} mr={125}>
            <Header />
            <Paper withBorder radius="xl" className={classes.sections}>
                <Flex direction="column" align="center">
                    <StatsComponent />
                </Flex>
            </Paper>
            <Paper withBorder radius="xl" className={classes.sections}>
                <DataSubmitForm
                    promptDefinitions={defaultPromptDefinitions}
                ></DataSubmitForm>
            </Paper>
            <Footer />
        </Box>
    );
}
