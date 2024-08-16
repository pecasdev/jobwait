import { assertEqual, Shape } from "./Shape";
import { StatMold, StatShape } from "./StatShape";

export type BarStatShape = StatShape & {
    type: "bar";
};

export class BarStatMold extends Shape<BarStatShape> {
    statMold = new StatMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "bar");
    }
}
