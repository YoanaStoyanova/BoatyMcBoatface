import { TransportTypeEnum } from './transport-type-model';
import { StationModel } from './station-model';
export class LineModel {

    constructor(public id: number,
        public name: string,
        public stations: Array<StationModel>,
        public selected: boolean,
        public transportType: TransportTypeEnum) { }
}