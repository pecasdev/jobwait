import { ChartData, ChartOptions } from "chart.js";
import { Bar } from "react-chartjs-2";
import { DataLabelsOptionsTop } from "./DataLabelsOptions";
import _ from "lodash";
import { DefaultChartOptions } from "./options/DefaultChartOptions";

export default function BarChart(props: {
    data: ChartData<"bar", any[], string>;
    options: ChartOptions<"bar">;
}) {
    const chartOptions: ChartOptions<"bar"> = _.merge({}, DefaultChartOptions, {
        plugins: {
            datalabels: DataLabelsOptionsTop
        }
    });

    return (
        <Bar
            data={props.data}
            options={_.merge({}, chartOptions, props.options)}
            width={200}
            height={200}
        />
    );
}
