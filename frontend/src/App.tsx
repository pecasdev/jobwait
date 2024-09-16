import { Header } from "./components/header/Header";
import { Box, Flex, LoadingOverlay, Paper } from "@mantine/core";
import { defaultPromptDefinitions } from "./models/DefaultPromptDefinitions";
import classes from "./App.module.css";
import { DataSubmitForm } from "./components/input/DataSubmitForm";
import { StatComponent } from "./components/stat/StatComponent";
import { Footer } from "./components/footer/Footer";
import { LinkedInLoginButton } from "./components/inputs/linkedinloginbutton/LinkedInLoginButton";
import { useState } from "react";
import { LoginCard } from "./components/inputs/LoginCard";

export default function App() {
    const [loginState, setLogin] = useState(false);
    return (
        <Box ml={125} mr={125}>
            <Header />
            <Paper withBorder radius="xl" className={classes.sections}>
                <Flex direction="column" align="center">
                    <StatComponent />
                </Flex>
            </Paper>
            <Paper
                pos="relative"
                withBorder
                radius="xl"
                className={classes.sections}
            >
                <LoadingOverlay
                    visible={!loginState}
                    overlayProps={{
                        radius: "xl",
                        backgroundOpacity: 0.99,
                    }}
                    loaderProps={{
                        children: (
                            <LoginCard>
                                <LinkedInLoginButton
                                    setLogin={setLogin}
                                ></LinkedInLoginButton>
                            </LoginCard>
                        ),
                    }}
                ></LoadingOverlay>
                <DataSubmitForm
                    promptDefinitions={defaultPromptDefinitions}
                ></DataSubmitForm>
            </Paper>
            <Footer />
        </Box>
    );
}
