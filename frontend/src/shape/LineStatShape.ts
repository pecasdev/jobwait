import { assertEqual, assertType, InvalidShapeError, Shape } from "./Shape";
import { StatMold } from "./StatShape";

export type LineStatRowShape = {
    [_: number]: number[];
};
export type LineStatShape = {
    type: "line";
    rows: LineStatRowShape;
};

class LineStatRowMold extends Shape<LineStatRowShape> {
    public assert(obj: any): LineStatRowShape {
        return super.assert(obj);
    }

    protected override throwIfMisfit(obj: any) {
        for (const key of Object.keys(obj)) {
            if (Number.isNaN(Number.parseInt(key))) {
                throw new InvalidShapeError("TYPE", "NaN", "number");
            }
            
            const list = obj[key];
            if (Array.isArray(list)) {
                list.every((x) => assertType(x, "number"));
            }
        }
    }
}

export class LineStatMold extends Shape<LineStatShape> {
    statMold = new StatMold();
    lineStatRowMold = new LineStatRowMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "line");
        this.lineStatRowMold.assert(obj["rows"]);
    }
}
