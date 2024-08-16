import { ArrayElement } from "../magic/ArrayElement";
import { assertElementOf, assertKeyInShape, assertType, Shape } from "./Shape";

const StatShapeTypes = ["bar", "line"];
export type StatRowsShape = {
    [key: string]: number | number[];
};
export type StatShape = {
    type: ArrayElement<typeof StatShapeTypes>;
    rows: StatRowsShape;
};

class StatRowMold extends Shape<StatRowsShape> {
    public assert(obj: any): StatRowsShape {
        return super.assert(obj);
    }

    protected override throwIfMisfit(obj: any) {
        for (const key of Object.keys(obj)) {
            if (Array.isArray(obj[key])) {
                obj[key].every((x) => assertType(x, "number"));
            } else {
                assertType(obj[key], "number");
            }
        }
    }
}

export class StatMold extends Shape<StatShape> {
    statRowMold = new StatRowMold();

    protected override throwIfMisfit(obj: any) {
        assertKeyInShape(obj, "type");
        assertElementOf(obj["type"], StatShapeTypes);
        assertKeyInShape(obj, "rows");
        this.statRowMold.assert(obj["rows"]);
    }
}
