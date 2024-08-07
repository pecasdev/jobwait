import { Chart, registerables } from "chart.js";
import { Footer } from "./reusable/Footer";
import { Header } from "./reusable/Header";
import { Divider, Flex, Paper } from "@mantine/core";
import { defaultPromptDefinitions } from "./reusable/default/DefaultPromptDefinitions";
import { DataSubmitForm } from "./reusable/DataSubmitForm";
import { StatsRoute } from "./routes/StatsRoute";

Chart.register(...registerables);

export default function App() {
    return (
        <Paper withBorder radius="xl" m={20} ml={100} mr={100}>
            <Header />
            <Flex direction="column" align="center">
                <StatsRoute></StatsRoute>
            </Flex>
            <Divider size="md" color="black" />
            <DataSubmitForm
                promptDefinitions={defaultPromptDefinitions}
            ></DataSubmitForm>
            <Footer />
        </Paper>
    );
}
