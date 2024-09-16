import { Card, Text, Stack } from "@mantine/core";

export function LoginCard(props: { children: React.ReactNode }) {
    return (
        <Card padding="md" radius="md" withBorder>
            <Stack justify="center" align="center" mt="xs" mb="xs" gap="sm">
                <Text fw={600} size="lg">
                    Please login to submit data.
                </Text>
                <Text size="sm" c="dimmed">
                    This is just to stop malicious actors from submitting bad
                    data.
                </Text>
                <Text size="sm" c="dimmed">
                    We do not store anything except the UUID provided by
                    LinkedIn.
                </Text>
            </Stack>

            {props.children}
        </Card>
    );
}
