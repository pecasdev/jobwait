import { Flex } from "@mantine/core";

export default function EmptyChart() {
    return (
        <Flex w={200} h={200}>
            <p className="w-full h-full text-center">Loading data...</p>
        </Flex>
    );
}
