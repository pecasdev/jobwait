import { ChartData, ChartOptions } from "chart.js";
import { Bubble } from "react-chartjs-2";
import { DataLabelsOptionsCenter } from "./DataLabelsOptions";
import _ from "lodash";
import { BubbleRow } from "../../../shape/shapes/BubbleStatShape";
import { Context } from "chartjs-plugin-datalabels";

export default function BubbleChart(props: {
    data: ChartData<"bubble", any[], string>;
    options: ChartOptions<"bubble">;
}) {
    function dataLabelsFormatter(_: any, context: Context): any | null {
        const bubble = context.dataset.data[context.dataIndex] as BubbleRow;
        return bubble.count;
    }

    const defaultOptions: ChartOptions<"bubble"> = {
        scales: {
            y: {
                beginAtZero: true,
            },
        },
        layout: {
            padding: {
                top: 40,
                left: 25,
                right: 25,
                bottom: 25,
            },
        },
        plugins: {
            title: { padding: { bottom: 35 } },
            datalabels: _.merge(DataLabelsOptionsCenter, {
                formatter: dataLabelsFormatter,
            }),
            legend: { position: "bottom" },
        },
    };

    return (
        <Bubble
            data={props.data}
            options={_.merge(defaultOptions, props.options)}
            width={200}
            height={200}
        />
    );
}
