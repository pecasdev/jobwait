import { assertEqual, assertType, Shape } from "./Shape";
import { StatMold } from "./StatShape";

export type BarStatRowShape = {
    [_: string]: number;
};
export type BarStatShape = {
    type: "bar";
    rows: BarStatRowShape;
};

class BarStatRowMold extends Shape<BarStatRowShape> {
    public assert(obj: any): BarStatRowShape {
        return super.assert(obj);
    }

    protected override throwIfMisfit(obj: any) {
        for (const key of Object.keys(obj)) {
            assertType(obj[key], "number");
        }
    }
}

export class BarStatMold extends Shape<BarStatShape> {
    statMold = new StatMold();
    barStatRowMold = new BarStatRowMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "bar");
        this.barStatRowMold.assert(obj["rows"]);
    }
}
