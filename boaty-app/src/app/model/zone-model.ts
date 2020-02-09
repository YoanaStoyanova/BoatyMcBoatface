import { StationModel } from './station-model';

export class ZoneModel {

    constructor(public id: number,
        public name: string,
        public stations: Array<StationModel>,
        public selected: boolean) { }

}