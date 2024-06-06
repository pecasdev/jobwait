import * as React from "react";
import { ColorRing } from "react-loader-spinner";

const spinningRing = (
    <ColorRing
        visible={true}
        height="40"
        width="40"
        ariaLabel="color-ring-loading"
        wrapperStyle={{}}
        wrapperClass="color-ring-wrapper"
        colors={["#e15b64", "#f47e60", "#f8b26a", "#abbd81", "#849b87"]}
    />
);

const checkMark = <p>✅</p>;

export function LoadingIcon({ showIcon, showLoading }: any) {
    if (!showIcon) return <p />;
    if (showLoading) return spinningRing;
    return checkMark;
}
