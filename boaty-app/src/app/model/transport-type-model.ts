export class TransportTypeModel {
    constructor(public id: TransportTypeEnum,
        public name: string,
        public icon: string,
        public selected: boolean) { }


}

export enum TransportTypeEnum {
    BUS = 1,
    SUBWAY,
    TRAIN,
    TRAM,
    SHUTTLE_VAN
}