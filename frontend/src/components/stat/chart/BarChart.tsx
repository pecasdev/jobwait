import { ChartData, ChartOptions } from "chart.js";
import { Bar } from "react-chartjs-2";
import { DataLabelsOptions } from "./DataLabelsOptions";

export default function BarChart(props: {
    data: ChartData<"bar", any[], string>;
}) {
    const options: ChartOptions<"bar"> = {
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

    return <Bar data={props.data} options={options} width={200} height={200} />;
}
