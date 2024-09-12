import { assertElementOf, assertKeyInShape, assertType, InvalidShapeError, ShapeMold } from "../ShapeMold";
import { StatRowsShape, StatShape, StatShapeTypes } from "../shapes/StatShape";
import { BarStatMold } from "./BarStatMold";
import { BubbleStatMold } from "./BubbleStatMold";
import { HistogramStatMold } from "./HistogramStatMold";
import { LineStatMold } from "./LineStatMold";

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

export function assertGenericStatShape(obj: any): StatShape {
    assertKeyInShape(obj, "type");
    assertElementOf(obj["type"], StatShapeTypes);

    switch(obj["type"]) {
        case "bar": return new BarStatMold().assert(obj);
        case "line": return new LineStatMold().assert(obj);
        case "bubble": return new BubbleStatMold().assert(obj);
        case "histogram": return new HistogramStatMold().assert(obj);
    }

    // impossible error, but we need to include this line to make typescript happy
    throw new InvalidShapeError();
}
