import { ChartData, ChartOptions } from "chart.js";
import * as React from "react";
import { Line } from "react-chartjs-2";

export default function LineChart(
    data: ChartData<"line", number[], string>,
    options: ChartOptions<"line">,
) {
    return (
        <span>
            <Line data={data} options={options} width="250px" />
        </span>
    );
}
