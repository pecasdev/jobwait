import { ChartOptions } from "chart.js";
import _ from "lodash";
import { BubbleStatMold } from "../../../shape/molds/BubbleStatMold";
import {
    BubbleStatShape
} from "../../../shape/shapes/BubbleStatShape";
import { StatShape } from "../../../shape/shapes/StatShape";
import BarChart from "./BarChart";
import BubbleChart, { formatBubbleStatForBubbleChart } from "./BubbleChart";
import HistogramChart from "./HistogramChart";
import LineChart from "./LineChart";

export default function GenericChart(props: {
    data: StatShape;
    legendName: string; // remove me
}) {
    const chartType = props.data.type;
    const chartTitle = props.data.title;
    const chartRows = props.data.rows;
    const chartXAxisLabel = props.data.xAxisLabel;
    const chartYAxisLabel = props.data.yAxisLabel;

    const chartData = {
        labels: Object.keys(chartRows),
        datasets: [
            {
                label: props.legendName,
                data: Object.values(chartRows),
            },
        ],
    };

    const chartOptions = {
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
    };

    switch (chartType) {
        case "bar":
            return <BarChart data={chartData} options={chartOptions} />;

        case "line":
            return <LineChart data={chartData} options={chartOptions} />;

        case "bubble":
            const bubbleStat: BubbleStatShape = new BubbleStatMold().assert(
                props.data,
            );

            const [xLabels, yLabels, bubbleRows] =
                formatBubbleStatForBubbleChart(bubbleStat);

            const bubbleChartOptions: ChartOptions<"bubble"> = _.merge(
                {},
                chartOptions,
                {
                    scales: {
                        x: {
                            type: "category",
                            labels: xLabels,
                            ticks: { padding: 20 },
                        },
                        y: {
                            type: "category",
                            labels: yLabels,
                            ticks: { padding: 20 },
                        },
                    },
                },
            ) as ChartOptions<"bubble">;

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
                    options={bubbleChartOptions}
                />
            );

        case "histogram":
            const histogramOptions = _.merge({}, chartOptions, {
                x: { ticks: { padding: 20 } },
            });
            return (
                <HistogramChart data={chartData} options={histogramOptions} />
            );

        default:
            return <p>yeah idk</p>;
    }
}
