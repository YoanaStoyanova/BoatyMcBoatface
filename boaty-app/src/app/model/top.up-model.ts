export class TopUpModel {

    constructor(public paymentType :string,
                public paymentMethodId :number,
                public amount :number) { }
}