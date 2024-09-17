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

    function scrollToStats () {
        const statHeader = document.getElementById("statHeader");
        if (statHeader != null) {
            statHeader.scrollIntoView({behavior: "smooth", block: "end"});
        }
    }

    function scrollToPrompts () {
        const promptHeader = document.getElementById("promptHeader");
        if (promptHeader != null) {
            promptHeader.scrollIntoView({behavior: "smooth", block:"start"});
        }
    }

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

                <Flex direction="row">

                <button onClick={scrollToStats}>View our stats</button>
                <button onClick={scrollToPrompts}>Help contribute answers</button>
                </Flex>
            </Flex>
        </header>
    );
}
