import {
    Text,
    Flex,
    useMantineColorScheme,
    useComputedColorScheme,
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
            <Flex direction="column" align="center" justify="center" pb={50}>
                <h1
                    onClick={() => toggleColorScheme()}
                    className={classes.title}
                >
                    <Text component="span" c="green" inherit>
                        job!
                    </Text>{" "}
                    <Text component="span" inherit>
                        wait...
                    </Text>
                </h1>

                <Text className={classes.description}>
                    Can I please just get a job?
                </Text>
            </Flex>
        </header>
    );
}
