import {
    Text,
    Flex,
    useMantineColorScheme,
    useComputedColorScheme,
} from "@mantine/core";
import classes from "./Header.module.css";
import useDetectScroll from "@smakss/react-scroll-direction";
import clsx from "clsx";

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

    function navigateToBlogPost() {
        window.location.href = "https://blog.pecas.dev/jobwait"
    }

    const {scrollPosition} = useDetectScroll();

    return (
        <header className={clsx({
            [classes.header]: true,
            [classes.dark_bg]: computedColorScheme === "dark",
            [classes.light_bg]: computedColorScheme === "light"
        })}>
            <Flex direction="column" align="center" justify="center" pb={15}>
                <h1
                    onClick={() => toggleColorScheme()}
                    className={clsx({
                        [classes.title_large]: scrollPosition.top < 50,
                        [classes.title_small]: scrollPosition.top >= 50,
                    })}
                >
                    <Text component="span" c="green" inherit>
                        jobwait
                    </Text>
                </h1>

                <Flex direction="row">

                <button className="p-1 mx-1" onClick={scrollToStats}>View our stats</button>
                <button className="p-1 mx-1" onClick={scrollToPrompts}>Help contribute answers</button>
                <button className="p-1 mx-1" onClick={navigateToBlogPost}>Read dev blog post</button>
                </Flex>
            </Flex>
        </header>
    );
}
