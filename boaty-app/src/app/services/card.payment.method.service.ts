import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from "../../environments/environment";
import {CardPaymentMethodModel} from "../model/card.payment.method-model";
import {PagedResponseModel} from "../model/paged.response-model";
import {AddCardModel} from "../model/add.card-model";

@Injectable({ providedIn: 'root' })
export class CardPaymentMethodService {
    constructor(private http: HttpClient) { }

    public listCards(userId :number) {
        return this.http.get<PagedResponseModel<CardPaymentMethodModel>>(`${environment.baseUrl}/payments/${userId}/cards`);
    }

    public deleteCard(cardId :number) {
        return this.http.delete<PagedResponseModel<CardPaymentMethodModel>>(`${environment.baseUrl}/payments/cards/${cardId}`);
    }

    public addCard(userId :number, card :AddCardModel) {
        return this.http.post<AddCardModel>(`${environment.baseUrl}/payments/${userId}/cards`, card);
    }
}
