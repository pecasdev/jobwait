import { StatShape } from "./StatShape";

export type BubbleRow = {
    x: string | number;
    y: string | number;
    r: number; // this gets overwritten by the chart
    count: number; // we want to normalize the radii or they'll get too big, orig_r stores the real value
};

export type BubbleStatShape = StatShape & {
    type: "bubble";
    rows: {
        [key: string]: BubbleRow;
    };
};
