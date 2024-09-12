import { ChartData, ChartOptions } from "chart.js";
import { Bubble } from "react-chartjs-2";
import { DataLabelsOptionsCenter } from "./DataLabelsOptions";
import _ from "lodash";
import { BubbleRow } from "../../../shape/shapes/BubbleStatShape";
import { Context } from "chartjs-plugin-datalabels";
import { DefaultChartOptions } from "./options/DefaultChartOptions";

export default function BubbleChart(props: {
    data: ChartData<"bubble", any[], string>;
    options: ChartOptions<"bubble">;
}) {
    function dataLabelsFormatter(_: any, context: Context): any | null {
        const bubble = context.dataset.data[context.dataIndex] as BubbleRow;
        return bubble.count;
    }

    const chartOptions: ChartOptions<"bubble"> = _.merge({}, DefaultChartOptions, {
        plugins: {
            datalabels: _.merge({}, DataLabelsOptionsCenter, {
                formatter: dataLabelsFormatter,
            }),
            legend: { display: false },
        },
    });

    return (
        <Bubble
            data={props.data}
            options={_.merge({}, chartOptions, props.options)}
            width={200}
            height={200}
        />
    );
}
