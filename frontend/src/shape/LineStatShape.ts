import { assertEqual, Shape } from "./Shape";
import { StatMold, StatShape } from "./StatShape";

export type LineStatShape = StatShape & {
    type: "line";
};

export class LineStatMold extends Shape<LineStatShape> {
    statMold = new StatMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "line");
    }
}
