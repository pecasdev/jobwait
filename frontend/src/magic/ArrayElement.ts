// evil typescript magic, I stole it from stack overflow
// don't stare at it too long, you'll go mad

export type ArrayElement<ArrayType extends readonly unknown[]> =
    ArrayType extends readonly (infer ElementType)[] ? ElementType : never;
