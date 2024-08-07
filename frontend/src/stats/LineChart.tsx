import { ChartData, ChartOptions } from "chart.js";
import { Line } from "react-chartjs-2";

export default function LineChart(
    data: ChartData<"line", number[], string>,
    options: ChartOptions<"line">,
) {
    return <Line data={data} options={options} width="200" height="200" />;
}
