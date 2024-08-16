import { ChartData, ChartOptions, plugins } from "chart.js";
import { Line } from "react-chartjs-2";

export default function LineChart(props: {
    data: ChartData<"line", any[], string>;
}) {
    const options: ChartOptions<"line"> = {
        plugins: {
            datalabels: {
                offset: 2,
                align: "top",

                anchor: "end",
                font: {
                    size: 16,
                },
            },
        },
    };
    return (
        <Line data={props.data} options={options} width={200} height={200} />
    );
}
