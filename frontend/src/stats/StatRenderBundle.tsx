// todo - rename this file

import { Component, ReactNode, useState } from "react";
import { LoadingIcon } from "../reusable/LoadingIcon";
import GraphExample from "./GraphExample";
import { irandom_range } from "../util/randomRange";
import { Box, LoadingOverlay } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";

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

export default function StatRenderBundle({
    queryPath,
    renderType,
}: StatRenderBundleProps) {
    const [renderData, setRenderData] = useState<any>(null);
    const [_, { toggle }] = useDisclosure(false);

    return (
        <Box
            align-items="center"
            justify-content="center"
            ml="lg"
            pos="relative"
            key={queryPath}
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
