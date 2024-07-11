// todo - rename this file

import { Component, ReactNode, useState } from "react";
import { LoadingIcon } from "../reusable/LoadingIcon";
import GraphExample from "./GraphExample";
import { irandom_range } from "../util/randomRange";

export type StatRenderType = "box" | "line";
export type StatRenderBundleProps = {
    queryPath: string;
    renderType: StatRenderType;
};

// todo - query the backend /stats/{queryPath} and get back some data
// todo - use the data above to update an internal state which re-renders the component
// todo - display some placeholder while the above takes place
export type StatRenderBundleState = {
    renderData: any | null;
};

export default function StatRenderBundle({queryPath, renderType}: StatRenderBundleProps) {
    const [renderData, setRenderData] = useState<any>(null);
    
    function dummySetData() {
        console.log("ye");
        setRenderData("something");
    }

    function renderStateData() {
        if (renderData == null) {
            return (
                <p className="text-center w-80 m-10">
                    grabbing your stats hngggggg
                    <LoadingIcon showIcon={true} showLoading={true} />
                </p>
            );
        } else {
            return <GraphExample />;
        }
    }

    function render() {
        return (
            <div className="flex w-64 h-64 bg-blue-200 align-middle items-center">
                {renderStateData()}
            </div>
        );
    }

    setTimeout(dummySetData, irandom_range(1000, 3000));
    return render();
}
