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
            title: { padding: { bottom: 35 } },
            datalabels: DataLabelsOptionsTop,
            legend: { display: false },
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
