import { Grid } from "@mantine/core";
import StatRenderBundle, {
    StatRenderBundleProps,
} from "../stats/StatRenderBundle";
import "./StatsRoute.css";

const statsToRender: StatRenderBundleProps[] = [
    { queryPath: "yoe-to-app-count", renderType: "line" },
    { queryPath: "yoe-to-app-count2", renderType: "line" },
    { queryPath: "yoe-to-app-count3", renderType: "line" },
    { queryPath: "yoe-to-app-count4", renderType: "line" },
];

function renderStats() {
    return statsToRender.map((props) => (
        <Grid.Col span={1}>
            <StatRenderBundle
                key={props.queryPath}
                queryPath={props.queryPath}
                renderType={props.renderType}
            />
        </Grid.Col>
    ));
}

export function StatsRoute() {
    return (
        <Grid
            columns={2}
            m="20px"
            w="800px"
            h="800px"
            justify="center"
            align="center"
        >
            {renderStats()}
        </Grid>
    );
}

// stat puller
// component thats given an endpoint and cache key
// component will check cache using key for existing data
// if doesn't exist or older than 1 hour, pull data and update local cache
// also show some kind of loading icon
