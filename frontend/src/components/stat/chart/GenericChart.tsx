import { StatShape } from "../../../shape/shapes/StatShape";
import BarChart from "./BarChart";
import LineChart from "./LineChart";

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
        
        case "line":
            return (
                <LineChart
                data={{
                    labels: Object.keys(chartData),
                    datasets: [
                        {
                            label: props.legendName,
                            data: Object.values(chartData)
                        }
                    ]
                }}
            />
            )

        default:
            return <p>yeah idk</p>;
    }
}
