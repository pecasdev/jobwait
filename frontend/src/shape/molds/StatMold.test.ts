import { describe, test, expect } from "@jest/globals";
import { StatMold } from "./StatMold";
import { InvalidShapeError } from "../ShapeMold";

describe("StatMold", () => {
    const goodObject = {
        type: "bar",
        xAxisLabel: "",
        yAxisLabel: "",
        rows: [],
    };
    const badObject = {
        type: "not bar or line",
        xAxisLabel: "",
        yAxisLabel: "",
        rows: [],
    };

    test("don't throw if object fits mold", () => {
        const toNotThrow = () => new StatMold().assert(goodObject);
        expect(toNotThrow).not.toThrow(InvalidShapeError)
    });

    test("throw if object doesn't fit mold", () => {
        const toThrow = () => new StatMold().assert(badObject);
        expect(toThrow).toThrowError(InvalidShapeError)
    })
});
