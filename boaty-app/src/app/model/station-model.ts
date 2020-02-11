export class StationModel {

    constructor(public id: number,
        public name: string,
        public zoneId: number,
        public zoneName: string,
        public selected: boolean) { }
}