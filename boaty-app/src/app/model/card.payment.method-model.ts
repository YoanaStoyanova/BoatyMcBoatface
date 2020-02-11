export class CardPaymentMethodModel {
    constructor(public id: number,
                public cardNumber: number,
                public cardHolder: string,
                public validity : string) { }
}