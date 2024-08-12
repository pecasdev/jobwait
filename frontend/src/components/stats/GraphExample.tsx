import LineChart from "./LineChart";

const labels = ["3 days ago", "2 days ago", "1 day ago", "today"];
const Data = [100, 90, 85, 87, 77, 60, 67, 45, 32];

export default function GraphExample(props: {
    currentColorScheme: "dark" | "light";
}) {
    const textColor =
        props.currentColorScheme === "dark" ? "#C9C9C9" : "#000000";

    const chartData = {
        labels,
        datasets: [
            {
                label: "important",
                data: Data,
            },
        ],
    };
    const chartOptions = {
        scales: {
            x: {
                ticks: {
                    color: textColor,
                },
            },
            y: {
                ticks: {
                    color: textColor,
                },
            },
        },

        plugins: {
            title: {
                display: true,
                text: "Cool Data",
                color: textColor,
                font: {
                    size: 18,
                },
            },
            legend: {
                labels: {
                    color: textColor,
                },
            },
        },
    };
    return LineChart(chartData, chartOptions, 200, 200);
}
