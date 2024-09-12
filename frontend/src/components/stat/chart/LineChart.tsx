import { ChartData, ChartOptions } from "chart.js";
import _ from "lodash";
import { Line } from "react-chartjs-2";
import { DataLabelsOptionsTop } from "./DataLabelsOptions";

export default function LineChart(props: {
    data: ChartData<"line", any[], string>;
    options: ChartOptions<"line">;
}) {
    const defaultOptions: ChartOptions<"line"> = {
        layout: {
            padding: 25,
        },
        plugins: {
            datalabels: DataLabelsOptionsTop,
            legend: { position: "bottom" },
        },
    };
    return (
        <Line
            data={props.data}
            options={_.merge(defaultOptions, props.options)}
            width={200}
            height={200}
        />
    );
}
