import { StatShape } from "./StatShape";

export type BubbleRow = {
    x: string | number;
    y: string | number;
    r: number; // this gets overwritten by the chart
    count: number;
};

export type BubbleStatShape = StatShape & {
    type: "bubble";
    rows: {
        [key: string]: BubbleRow;
    };
};
