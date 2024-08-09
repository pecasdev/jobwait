import {
    Text,
    Flex,
    useMantineColorScheme,
    useComputedColorScheme,
    Box,
} from "@mantine/core";
import classes from "./Header.module.css";

export function Header() {
    const { setColorScheme } = useMantineColorScheme();
    const computedColorScheme = useComputedColorScheme("light");
    const toggleColorScheme = () => {
        setColorScheme(computedColorScheme === "dark" ? "light" : "dark");
    };

    return (
        <header>
            <Box>
                <Flex
                    direction="column"
                    align="center"
                    justify="center"
                    pb={50}
                >
                    <h1
                        onClick={() => toggleColorScheme()}
                        className={classes.title}
                    >
                        <Text component="span" c="yellow" inherit>
                            job!
                        </Text>{" "}
                        <Text component="span" c="black" inherit>
                            wait...
                        </Text>{" "}
                    </h1>

                    <Text className={classes.description} color="dimmed">
                        Can I please just get a job?
                    </Text>
                </Flex>
            </Box>
        </header>
    );
}
