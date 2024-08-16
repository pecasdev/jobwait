import { assertElementOf, assertKeyInShape, assertType, Shape } from "./Shape";

const StatShapeTypes = ["bar", "line"];
export type StatRowShape = { [_: string]: any };
export type StatShape = {
    type: (typeof StatShapeTypes)[number];
    rows: StatRowShape;
};

export class StatMold extends Shape<StatShape> {
    protected override throwIfMisfit(obj: any) {
        assertKeyInShape(obj, "type");
        assertElementOf(obj["type"], StatShapeTypes);
        assertKeyInShape(obj, "rows");
    }
}
