import { ZoneModel } from 'src/app/model/zone-model';
import { LineModel } from './line-model';

export class TicketModel {

    constructor(public id: number,
        public minutesValidFor: number,
        public price: number,
        public name: string,
        public zones: Array<ZoneModel>,
        public lines: Array<LineModel>,
        public additionalLines: Array<LineModel>) { }
}