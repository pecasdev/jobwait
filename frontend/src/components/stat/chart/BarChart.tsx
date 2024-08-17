import { ChartData, ChartOptions } from "chart.js";
import { Bar } from "react-chartjs-2";
import { DataLabelsOptions } from "./DataLabelsOptions";
import _ from "lodash";

export default function BarChart(props: {
    data: ChartData<"bar", any[], string>;
    options: ChartOptions<"bar">;
}) {
    const defaultOptions: ChartOptions<"bar"> = {
        scales: {
            y: {
                beginAtZero: true,
            },
        },
        layout: {
            padding: {
                top: 40,
                left: 25,
                right: 25,
                bottom: 25,
            },
        },
        plugins: {
            datalabels: DataLabelsOptions,
            legend: { position: "bottom" },
        },
    };

    return (
        <Bar
            data={props.data}
            options={_.merge(defaultOptions, props.options)}
            width={200}
            height={200}
        />
    );
}
