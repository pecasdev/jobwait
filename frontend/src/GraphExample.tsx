import * as React from "react";
import { Line } from "react-chartjs-2";

const labels = ["3 days ago", "2 days ago", "1 day ago", "today", "tomorrow"];
const Data = [100, 90, 85, 87, 77, 60, 67, 45, 32];

export default function GraphExample() {
    const chartData = {
        labels,
        datasets: [{ label: "🤪", data: Data }],
    };

    return (
        <span className="max-h-80 max-w-96">
            <Line
                data={chartData}
                options={{
                    plugins: {
                        title: { display: true, text: "peter's will to live" },
                    },
                }}
            />
        </span>
    );
}
