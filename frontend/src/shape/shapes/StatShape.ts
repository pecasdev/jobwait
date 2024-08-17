import { ArrayElement } from "../../magic/ArrayElement";

export const StatShapeTypes = ["bar", "line"];
export type StatRowsShape = {
    [key: string]: number | number[];
};
export type StatShape = {
    type: ArrayElement<typeof StatShapeTypes>;
    title: string;
    xAxisLabel: string;
    yAxisLabel: string;
    rows: StatRowsShape;
};
