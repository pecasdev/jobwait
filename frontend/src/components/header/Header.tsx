import {
    Text,
    Flex,
    useMantineColorScheme,
    useComputedColorScheme,
} from "@mantine/core";
import classes from "./Header.module.css";
import useDetectScroll from "@smakss/react-scroll-direction";

export function Header() {
    const { setColorScheme } = useMantineColorScheme();
    const computedColorScheme = useComputedColorScheme("light");
    const toggleColorScheme = () => {
        setColorScheme(computedColorScheme === "dark" ? "light" : "dark");
    };
    const {scrollPosition} = useDetectScroll();

    return (
        <header className={classes.header}>
            <Flex direction="column" align="center" justify="center" pb={15}>
                <h1
                    onClick={() => toggleColorScheme()}
                    className={(scrollPosition.top < 50) ? classes.title_large: classes.title_small}
                >
                    <Text component="span" c="green" inherit>
                        jobwait
                    </Text>
                </h1>

                <Text className={classes.description}>
                    Can I please just get a job?
                </Text>
            </Flex>
        </header>
    );
}
