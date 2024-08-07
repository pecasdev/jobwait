import { Divider, Flex, Title } from "@mantine/core";

function peterContact() {
    return (
        <a href="https://github.com/pecasdev" target="_blank">
            peter
        </a>
    );
}

function danielContact() {
    return (
        <a href="https://github.com/danielperev" target="_blank">
            daniel
        </a>
    );
}

export function Footer() {
    return (
        <footer>
            <Divider size="md" color="black" />
            <Flex direction="column" align="center" justify="center">
                <Title fw={400} fz={"lg"}>
                    give us jobs: {peterContact()}, {danielContact()}
                </Title>
            </Flex>
        </footer>
    );
}
