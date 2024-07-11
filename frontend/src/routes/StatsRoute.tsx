import GraphExample from "../stats/GraphExample";
import StatRenderBundle, {
    StatRenderBundleProps,
} from "../stats/StatRenderBundle";
import "./StatsRoute.css";

const statsToRender: StatRenderBundleProps[] = [
    { queryPath: "yoe-to-app-count", renderType: "line" },
    { queryPath: "yoe-to-app-count2", renderType: "line" },
    { queryPath: "yoe-to-app-count3", renderType: "line" },
    { queryPath: "yoe-to-app-count4", renderType: "line" },
    { queryPath: "yoe-to-app-count5", renderType: "line" },
    { queryPath: "yoe-to-app-count6", renderType: "line" },
];

function renderStats() {
    return statsToRender.map((props) => (
        <StatRenderBundle
            key={props.queryPath}
            queryPath={props.queryPath}
            renderType={props.renderType}
        />
    ));
}

export function StatsRoute() {
    return <div className="grid grid-auto-cols gap-4">{renderStats()}</div>;
}

// stat puller
// component thats given an endpoint and cache key
// component will check cache using key for existing data
// if doesn't exist or older than 1 hour, pull data and update local cache
// also show some kind of loading icon
