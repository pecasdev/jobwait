import { assertEqual, assertKeyInShape, ShapeMold } from "../ShapeMold";
import { BubbleStatShape } from "../shapes/BubbleStatShape";
import { StatMold } from "./StatMold";

export class BubbleStatMold extends ShapeMold<BubbleStatShape> {
    statMold = new StatMold();

    protected override throwIfMisfit(obj: any) {
        this.statMold.assert(obj);
        assertEqual(obj["type"], "bubble");

        const rows = obj["rows"];
        const bubbles = Object.values(rows);

        for (const bubble of bubbles) {
            assertKeyInShape(bubble, "x");
            assertKeyInShape(bubble, "y");
            assertKeyInShape(bubble, "count");
        }
    }
}
