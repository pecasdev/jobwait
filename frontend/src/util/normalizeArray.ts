import _ from "lodash";

export function normalizeArray(
    arr: number[],
    new_min: number,
    new_max: number,
): number[] {
    if (arr.length == 0) {
        return [];
    }
    const min = _.min(arr) as number;
    const max = _.max(arr) as number;

    return arr.map(
        (value) =>
            ((value - min) / (max - min)) * (new_max - new_min) + new_min,
    );
}
