import { Header } from "./components/header/Header";
import { Box, Flex, Paper } from "@mantine/core";
import { defaultPromptDefinitions } from "./model/DefaultPromptDefinitions";

import classes from "./App.module.css";
import { DataSubmitForm } from "./components/input/DataSubmitForm";
import { StatComponent } from "./components/stat/StatComponent";
import { Footer } from "./components/footer/Footer";

export default function App() {
    return (
        <Box ml={125} mr={125}>
            <Header />
            
            <h2 id="statHeader">Stats we have compiled with your answers!</h2>
            <Paper withBorder radius="xl" className={classes.sections}>
                <Flex direction="column" align="center">
                    <StatComponent />
                </Flex>
            </Paper>
            
            <h2 id="promptHeader" className="p-0 m-0">Help us display more stats by answering some questions!</h2>
            <h4 className="p-0 m-0">(Answer all questions for a reward)</h4>
        
            <Paper withBorder radius="xl" className={classes.sections}>
                <DataSubmitForm
                    promptDefinitions={defaultPromptDefinitions}
                ></DataSubmitForm>
            </Paper>
            <Footer />
        </Box>
    );
}
