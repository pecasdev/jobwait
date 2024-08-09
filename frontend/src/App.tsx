import { Chart, registerables } from "chart.js";
import { Footer } from "./reusable/Footer";
import { Header } from "./reusable/header/Header";
import { Box, Flex, Paper } from "@mantine/core";
import { defaultPromptDefinitions } from "./reusable/default/DefaultPromptDefinitions";
import { DataSubmitForm } from "./reusable/DataSubmitForm";
import { StatsRoute } from "./routes/StatsRoute";

Chart.register(...registerables);

export default function App() {
    return (
        <body>
            <Box m={20} ml={100} mr={100}>
                <Header />
                <Paper withBorder radius="xl" m={10} ml={100} mr={100}>
                    <Flex direction="column" align="center">
                        <StatsRoute></StatsRoute>
                    </Flex>
                </Paper>
                <Paper withBorder radius="xl" m={10} ml={100} mr={100}>
                    <DataSubmitForm
                        promptDefinitions={defaultPromptDefinitions}
                    ></DataSubmitForm>
                </Paper>
                <Footer />
            </Box>
        </body>
    );
}
