import { Header } from "./components/header/Header";
import { Box, Flex, Paper } from "@mantine/core";
import { defaultPromptDefinitions } from "./model/DefaultPromptDefinitions";

import classes from "./App.module.css";
import { DataSubmitForm } from "./components/input/DataSubmitForm";
import { StatComponent } from "./components/stat/StatComponent";
import { Footer } from "./components/footer/Footer";
import { LinkedInPage } from "./components/inputs/linkedinloginbutton/LinkedInLoginButton";

export default function App() {
    return (
        <Box ml={125} mr={125}>
            <LinkedInPage></LinkedInPage>
            <Header />
            <Paper withBorder radius="xl" className={classes.sections}>
                <Flex direction="column" align="center">
                    <StatComponent />
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
