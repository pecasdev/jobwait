import { ChartData, ChartOptions } from "chart.js";
import { Line } from "react-chartjs-2";

export default function LineChart(
    data: ChartData<"line", number[], string>,
    options: ChartOptions<"line">,
    width: number,
    height: number,
) {
    return <Line data={data} options={options} width={width} height={height} />;
}
