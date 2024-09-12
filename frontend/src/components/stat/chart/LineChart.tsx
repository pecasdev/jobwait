import { ChartData, ChartOptions } from "chart.js";
import _ from "lodash";
import { Line } from "react-chartjs-2";
import { DataLabelsOptionsTop } from "./DataLabelsOptions";
import { DefaultChartOptions } from "./options/DefaultChartOptions";

export default function LineChart(props: {
    data: ChartData<"line", any[], string>;
    options: ChartOptions<"line">;
}) {
    const chartOptions: ChartOptions<"line"> = _.merge({}, 
        DefaultChartOptions, {
            plugins: {
                datalabels: DataLabelsOptionsTop
            }
        }
    )

    return (
        <Line
            data={props.data}
            options={_.merge({}, chartOptions, props.options)}
            width={200}
            height={200}
        />
    );
}
