import { Divider, Flex, Title } from "@mantine/core";

export function Header() {
    return (
        <header>
            <Flex direction="column" align="center" justify="center">
                <Title fw={800}>job! wait...</Title>
            </Flex>
            <Divider size="md" color="black" />
        </header>
    );
}
