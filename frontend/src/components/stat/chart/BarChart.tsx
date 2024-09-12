import { ChartData, ChartOptions } from "chart.js";
import { Bar } from "react-chartjs-2";
import { DataLabelsOptionsTop } from "./DataLabelsOptions";
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
            title: { padding: { bottom: 25 } },
            datalabels: DataLabelsOptionsTop,
            legend: { display: false },
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
