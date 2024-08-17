import { StatShape } from "../../../shape/shapes/StatShape";
import BarChart from "./BarChart";
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

        default:
            return <p>yeah idk</p>;
    }
}
