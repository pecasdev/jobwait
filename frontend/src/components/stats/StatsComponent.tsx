import { Box, Grid, LoadingOverlay } from "@mantine/core";
import { useState } from "react";
import { useDisclosure } from "@mantine/hooks";
import GraphExample from "./GraphExample";

export type StatRenderType = "box" | "line";
export type StatRenderBundleProps = {
    queryPath: string;
    renderType: StatRenderType;
};

// todo - query the backend /stats/{queryPath} and get back some data
// todo - use the data above to update an internal state which re-renders the component
// todo - display some placeholder while the above takes place
export type StatRenderBundleState = {
    renderData: any | null;
};

const statsToRender = [
    { queryPath: "yoe-to-app-count", renderType: "line" },
    { queryPath: "yoe-to-app-count2", renderType: "line" },
    { queryPath: "yoe-to-app-count3", renderType: "line" },
    { queryPath: "yoe-to-app-count4", renderType: "line" },
];

function StatRenderBundle() {
    const [renderData, setRenderData] = useState<any>(null);
    const [_, { toggle }] = useDisclosure(false);

    return (
        <Box
            align-items="center"
            justify-content="center"
            ml="lg"
            pos="relative"
        >
            <LoadingOverlay
                visible={renderData == null}
                overlayProps={{
                    radius: "sm",
                    backgroundOpacity: 0.9,
                    blur: 1,
                }}
                onClick={() => {
                    setRenderData("test");
                    toggle;
                }}
            ></LoadingOverlay>
            <GraphExample />
        </Box>
    );
}

export function StatsComponent() {
    let stats = statsToRender.map((props) => (
        <Grid.Col span={1} id={props.queryPath}>
            <StatRenderBundle key={props.queryPath} />
        </Grid.Col>
    ));

    return (
        <Grid
            columns={2}
            m="20px"
            w="800px"
            h="800px"
            justify="center"
            align="center"
        >
            {...stats}
        </Grid>
    );
}

// stat puller
// component thats given an endpoint and cache key
// component will check cache using key for existing data
// if doesn't exist or older than 1 hour, pull data and update local cache
// also show some kind of loading icon
