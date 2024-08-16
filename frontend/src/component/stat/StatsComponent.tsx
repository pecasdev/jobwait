import {
    Box,
    Grid,
    LoadingOverlay,
    useComputedColorScheme,
} from "@mantine/core";
import clsx from "clsx";
import { useEffect, useState } from "react";
import Api from "../../api";
import { StatShape } from "../../shape/StatShape";
import GenericChart from "./chart/GenericChart";
import classes from "./StatsComponent.module.css";

const statsToRender = [{ statId: "job-title" }];

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

export function StatsComponent() {
    let stats = statsToRender.map((stat) => (
        <Grid.Col span={1} id={stat.statId}>
            <StatRenderBundle statId={stat.statId} />
        </Grid.Col>
    ));

    return (
        <Grid columns={2} className={classes.grid}>
            {...stats}
        </Grid>
    );
}