import {
    Box,
    Grid,
    LoadingOverlay,
    useComputedColorScheme,
} from "@mantine/core";
import clsx from "clsx";
import { useEffect, useState } from "react";
import Api from "../../api";
import { StatShape } from "../../shape/shapes/StatShape";
import GenericChart from "./chart/GenericChart";
import classes from "./StatComponent.module.css";

// todo - don't hardcode this list in the frontend, have it be fetched from the backend
const statIdsToRender = ["job-title", "job-wait", "apps-needed-given-job-count"];

function StatRenderBundle(props: { statId: string }) {
    const computedColorScheme = useComputedColorScheme("light");
    const [renderData, setRenderData] = useState<StatShape | null>(null);

    // todo: investigate bug with duplicate requests going out
    // nothing breaks but we definitely shouldn't be sending duplicate requests for no reason lol
    // honestly might leave fixing this bug until after MVP is done
    useEffect(() => {
        const api = new Api();
        const statId = props.statId;
        const fetchAndSet = () => {
            console.log("fetching");
            api.fetchStat(statId)
                .then(setRenderData)
                .catch((error) => console.error(error));
        };
        setTimeout(fetchAndSet, 2000); // fake delay to test loading animation
    }, []);

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
            />

            {renderData ? (
                <GenericChart data={renderData} legendName={props.statId} />
            ) : null}
        </Box>
    );
}

export function StatComponent() {
    let stats = statIdsToRender.map((statId) => (
        <Grid.Col span={1} id={statId}>
            <StatRenderBundle statId={statId} />
        </Grid.Col>
    ));

    return (
        <Grid columns={2} className={classes.grid}>
            {...stats}
        </Grid>
    );
}