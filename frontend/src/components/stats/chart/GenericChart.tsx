import { StatShape } from "../../../shape/StatShape";
import BarChart from "./BarChart";

export default function GenericChart(props: {
    data: StatShape;
    legendName: string;
}) {
    const chartType = props.data.type;
    const chartData = props.data.rows;

    switch (chartType) {
        case "bar":
            return (
                <BarChart
                    data={{
                        labels: Object.keys(chartData),
                        datasets: [
                            {
                                label: props.legendName,
                                data: Object.values(chartData),
                            },
                        ],
                    }}
                />
            );

        default:
            return <p>yeah idk</p>;
    }
}
