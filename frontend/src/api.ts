import { StatMold, StatShape } from "./shape/StatShape";

export default class Api {
    static backendUrl = "http://127.0.0.1:9000";

    async fetchStat(statId: string): Promise<StatShape> {
        return fetch(`${Api.backendUrl}/stat?id=${statId}`)
            .then((resp) => resp.json())
            .then((json) => json["data"])
            .then((data) => new StatMold().assert(data));
    }
}
