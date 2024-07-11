import LineChart from "./LineChart";

const labels = ["3 days ago", "2 days ago", "1 day ago", "today"];
const Data = [100, 90, 85, 87, 77, 60, 67, 45, 32];

export default function GraphExample() {
    const chartData = {
        labels,
        datasets: [{ label: "something", data: Data }],
    };
    const chartOptions = {
        plugins: {
            title: { display: true, text: "some graph" },
        },
    };

    return LineChart(chartData, chartOptions);
}
