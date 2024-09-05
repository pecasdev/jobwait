import { StatShape } from "./shape/shapes/StatShape";
import { StatMold } from "./shape/molds/StatMold";

import _ from "lodash";

export default class Api {
    static backendUrl = process.env.REACT_APP_API_URL;

    async fetchStat(statId: string): Promise<StatShape> {
        return fetch(`${Api.backendUrl}/stat?id=${statId}`)
            .then((resp) => resp.json())
            .then((json) => json["data"])
            .then((data) => new StatMold().assert(data))
            .then((stat) => {
                // sort row keys
                stat.rows = _.pick(stat.rows, Object.keys(stat.rows).sort());
                return stat;
            });
    }
}
