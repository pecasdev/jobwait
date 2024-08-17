import { assertEqual, ShapeMold } from "../ShapeMold";
import { LineStatShape } from "../shapes/LineStatShape";
import { StatMold } from "./StatMold";

export class LineStatMold extends ShapeMold<LineStatShape> {
    statMold = new StatMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "line");
    }
}
