import { ChartData, ChartOptions } from "chart.js";
import _ from "lodash";
import { Line } from "react-chartjs-2";
import { DataLabelsOptions } from "./DataLabelsOptions";

export default function LineChart(props: {
    data: ChartData<"line", any[], string>;
}) {
    const options: ChartOptions<"line"> = {
        layout: {
            padding: 25,
        },
        plugins: {
            datalabels: DataLabelsOptions,
            legend: { position: "bottom" },
        },
    };
    return (
        <Line data={props.data} options={options} width={200} height={200} />
    );
}
