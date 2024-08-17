import { assertElementOf, assertKeyInShape, assertType, ShapeMold } from "../ShapeMold";
import { StatRowsShape, StatShape, StatShapeTypes } from "../shapes/StatShape";

class StatRowMold extends ShapeMold<StatRowsShape> {
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

export class StatMold extends ShapeMold<StatShape> {
    statRowMold = new StatRowMold();

    protected override throwIfMisfit(obj: any) {
        assertKeyInShape(obj, "type");
        assertElementOf(obj["type"], StatShapeTypes);
        
        assertKeyInShape(obj, "xAxisLabel");
        assertType(obj["xAxisLabel"], "string");

        assertKeyInShape(obj, "yAxisLabel");
        assertType(obj["yAxisLabel"], "string");

        assertKeyInShape(obj, "rows");
        this.statRowMold.assert(obj["rows"]);
    }
}
