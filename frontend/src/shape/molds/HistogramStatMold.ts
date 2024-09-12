import { assertEqual, ShapeMold } from "../ShapeMold";
import { HistogramStatShape } from "../shapes/HistogramStatShape";
import { StatMold } from "./StatMold";

export class HistogramStatMold extends ShapeMold<HistogramStatShape> {
    statMold = new StatMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "histogram");
    }
}
