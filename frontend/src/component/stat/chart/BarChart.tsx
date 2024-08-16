import { ChartData, ChartOptions } from "chart.js";
import { Bar } from "react-chartjs-2";

export default function BarChart(props: {
    data: ChartData<"bar", any[], string>;
}) {
    const options: ChartOptions<"bar"> = {
        scales: {
            y: {
                beginAtZero: true,
            },
        },
    };

    return <Bar data={props.data} options={options} width={200} height={200} />;
}
