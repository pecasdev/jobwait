import {
    Box,
    Grid,
    LoadingOverlay,
    useComputedColorScheme,
} from "@mantine/core";
import { useState } from "react";
import { useDisclosure } from "@mantine/hooks";
import classes from "./StatsComponent.module.css";
import GraphExample from "./GraphExample";
import clsx from "clsx";

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
    const computedColorScheme = useComputedColorScheme("light");
    const [renderData, setRenderData] = useState<any>(null);
    const [_, { toggle }] = useDisclosure(false);
    return (
        <Box
            pos="relative"
            className={clsx({
                [classes.dark_bg]: computedColorScheme === "dark",
                [classes.light_bg]: computedColorScheme === "light",
            })}
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
            <GraphExample
                currentColorScheme={computedColorScheme}
            ></GraphExample>
        </Box>
    );
}

export function StatsComponent() {
    let stats = statsToRender.map((stat) => (
        <Grid.Col span={1} id={stat.queryPath}>
            <StatRenderBundle />
        </Grid.Col>
    ));

    return (
        <Grid columns={2} className={classes.grid}>
            {...stats}
        </Grid>
    );
}

// stat puller
// component thats given an endpoint and cache key
// component will check cache using key for existing data
// if doesn't exist or older than 1 hour, pull data and update local cache
// also show some kind of loading icon
