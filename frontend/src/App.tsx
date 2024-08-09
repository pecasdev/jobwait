import { Chart, registerables } from "chart.js";
import { Footer } from "./components/footer/Footer";
import { Header } from "./components/header/Header";
import { Box, Flex, Paper, rgba } from "@mantine/core";
import { defaultPromptDefinitions } from "./models/DefaultPromptDefinitions";

import "./App.css";
import { DataSubmitForm } from "./components/inputs/DataSubmitForm";
import { StatsComponent } from "./components/stats/StatsComponent";

Chart.register(...registerables);
Chart.defaults.color = rgba("0, 0, 0", 1); //change chart label to be darker

export default function App() {
    return (
        <Box m={20} ml={100} mr={100}>
            <Header />
            <Paper withBorder radius="xl" m={10} ml={100} mr={100}>
                <Flex direction="column" align="center">
                    <StatsComponent></StatsComponent>
                </Flex>
            </Paper>
            <Paper withBorder radius="xl" m={10} ml={100} mr={100}>
                <DataSubmitForm
                    promptDefinitions={defaultPromptDefinitions}
                ></DataSubmitForm>
            </Paper>
            <Footer />
        </Box>
    );
}
