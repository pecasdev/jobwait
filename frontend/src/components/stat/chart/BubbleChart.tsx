import { ChartData, ChartOptions } from "chart.js";
import { Bubble } from "react-chartjs-2";
import { DataLabelsOptionsCenter } from "./DataLabelsOptions";
import _ from "lodash";
import { BubbleRow, BubbleStatShape } from "../../../shape/shapes/BubbleStatShape";
import { Context } from "chartjs-plugin-datalabels";
import { DefaultChartOptions } from "./options/DefaultChartOptions";
import { normalizeArray } from "../../../util/normalizeArray";

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

export function formatBubbleStatForBubbleChart(bubbleStat: BubbleStatShape): [string[], string[], BubbleRow[]] {
    const bubbleRows: BubbleRow[] = Object.values(bubbleStat.rows);
    const normalizedRadii = normalizeArray(
        bubbleRows.map((b) => b.count),
        10,
        30,
    );
    normalizedRadii.forEach((normal_count, i) => {
        bubbleRows[i].r = Math.round(normal_count);
    });

    const xLabels = [
        ...new Set(bubbleRows.map((row) => row.x.toString())),
    ];
    const yLabels = [
        ...new Set(bubbleRows.map((row) => row.y.toString())),
    ];

    return [xLabels, yLabels, bubbleRows];
}
