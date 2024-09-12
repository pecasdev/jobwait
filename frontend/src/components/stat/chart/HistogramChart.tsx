import { ChartData, ChartOptions } from "chart.js";
import { Bar } from "react-chartjs-2";
import _ from "lodash";
import { DefaultChartOptions } from "./options/DefaultChartOptions";

export default function HistogramChart(props: {
    data: ChartData<"bar", any[], string>;
    options: ChartOptions<"bar">;
}) {
    const chartOptions: ChartOptions<"bar"> = _.merge({}, 
        DefaultChartOptions, {
            datasets: { bar: { barPercentage: 1.0, categoryPercentage: 1.0 } },
            plugins: {
                datalabels: { display: false }
            }
        }
    )
    

    return (
        <Bar
            data={props.data}
            options={_.merge({}, chartOptions, props.options)}
            width={200}
            height={200}
        />
    );
}
