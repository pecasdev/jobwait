
import { assertEqual, ShapeMold } from "../ShapeMold";
import { BarStatShape } from "../shapes/BarStatShape";
import { StatMold } from "./StatMold";

export class BarStatMold extends ShapeMold<BarStatShape> {
    statMold = new StatMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "bar");
    }
}