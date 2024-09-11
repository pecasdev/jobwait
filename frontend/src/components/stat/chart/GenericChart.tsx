import { BubbleStatMold } from "../../../shape/molds/BubbleStatMold";
import { BubbleStatShape } from "../../../shape/shapes/BubbleStatShape";
import { StatShape } from "../../../shape/shapes/StatShape";
import { normalizeArray } from "../../../util/normalizeArray";
import BarChart from "./BarChart";
import BubbleChart from "./BubbleChart";
import LineChart from "./LineChart";

export default function GenericChart(props: {
    data: StatShape;
    legendName: string;
}) {
    const chartType = props.data.type;
    const chartTitle = props.data.title;
    const chartData = props.data.rows;
    const chartXAxisLabel = props.data.xAxisLabel;
    const chartYAxisLabel = props.data.yAxisLabel;

    switch (chartType) {
        case "bar":
            return (
                <BarChart
                    data={{
                        labels: Object.keys(chartData),

                        datasets: [
                            {
                                label: props.legendName,
                                data: Object.values(chartData),
                            },
                        ],
                    }}
                    options={{
                        plugins: {
                            title: {
                                display: true,
                                text: chartTitle,
                            },
                        },
                        scales: {
                            x: {
                                title: {
                                    display: true,
                                    text: chartXAxisLabel,
                                },
                            },
                            y: {
                                title: {
                                    display: true,
                                    text: chartYAxisLabel,
                                },
                            },
                        },
                    }}
                />
            );

        case "line":
            return (
                <LineChart
                    data={{
                        labels: Object.keys(chartData),
                        datasets: [
                            {
                                label: props.legendName,
                                data: Object.values(chartData),
                            },
                        ],
                    }}
                    options={{
                        plugins: {
                            title: {
                                display: true,
                                text: chartTitle,
                            },
                        },
                        scales: {
                            x: {
                                title: {
                                    display: true,
                                    text: chartXAxisLabel,
                                },
                            },
                            y: {
                                title: {
                                    display: true,
                                    text: chartYAxisLabel,
                                },
                            },
                        },
                    }}
                />
            );

        case "bubble":
            const bubbleStat: BubbleStatShape = new BubbleStatMold().assert(props.data);
            const bubbleRows = Object.values(bubbleStat.rows);
            const normalizedRadii = normalizeArray(bubbleRows.map(b => b.count), 10, 30);
            normalizedRadii.forEach((normal_count, i) => {
                bubbleRows[i].r = Math.round(normal_count);
            });

            const xLabels = [... new Set(bubbleRows.map(row => row.x.toString()))];
            const yLabels = [... new Set(bubbleRows.map(row => row.y.toString()))];

            return (
                <BubbleChart
                    data={{
                        datasets: [
                            {
                                label: props.legendName,
                                data: bubbleRows,
                            },
                        ],
                    }}
                    options={{
                        plugins: {
                            title: {
                                display: true,
                                text: chartTitle,
                            },
                        },
                        scales: {
                            x: {
                                type: "category",
                                labels: xLabels,
                                ticks: {padding: 20},
                                title: {
                                    display: true,
                                    text: chartXAxisLabel,
                                },
                            },
                            y: {
                                type: "category",
                                labels: yLabels,
                                ticks: {padding: 20},
                                title: {
                                    display: true,
                                    text: chartYAxisLabel,
                                },
                            },
                        },
                    }}
                />
            );

        default:
            return <p>yeah idk</p>;
    }
}
