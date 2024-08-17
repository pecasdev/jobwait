import { ArrayElement } from "../magic/ArrayElement";
import {
    InvalidShapeErrorProblem,
    KnownJavascriptType,
    KnownJavascriptTypes,
} from "./Shape";

export abstract class ShapeMold<A> {
    protected abstract throwIfMisfit(obj: any): void;

    public assert(obj: any): A {
        try {
            this.throwIfMisfit(obj);
            return obj as A;
        } catch (e) {
            if (!(e instanceof InvalidShapeError)) {
                throw new InvalidShapeError();
            } else {
                throw e;
            }
        }
    }
}

export class InvalidShapeError extends Error {
    constructor(
        problemType: InvalidShapeErrorProblem | null = null,
        actual: any = "",
        expected: any = "",
    ) {
        let description;

        switch (problemType) {
            default:
                description = "";
            case "KEY":
                description = `Could not find expected key: '${expected}'`;
            case "TYPE":
                description = `Mismatch type, expected: '${expected}', got: '${actual}'`;
            case "EQUALITY":
                description = `Unequal values, expected: '${expected}', got: '${actual}`;
            case "INCLUSION":
                description = `Expected value ${actual} to be an element of [${expected}]`;
        }

        super(`SHAPE_ERROR: ${description}`);
    }
}

export function assertKeyInShape(object: any, key: string) {
    if (!object.hasOwnProperty(key)) {
        throw new InvalidShapeError("KEY", undefined, key);
    }
}

export function assertType<A extends ArrayElement<KnownJavascriptType>>(
    object: any,
    type: A,
) {
    if (KnownJavascriptTypes.includes(typeof object)) {
        return object as typeof type;
    }
    throw new InvalidShapeError("TYPE", type, undefined);
}

export function assertElementOf(object: any, list: any[]) {
    if (list.includes(object)) {
        return object;
    }
    throw new InvalidShapeError("INCLUSION", object, list);
}

export function assertEqual<A>(object: any, expected: A) {
    if (object == expected) {
        return object;
    }
    return new InvalidShapeError("EQUALITY", object, expected);
}
