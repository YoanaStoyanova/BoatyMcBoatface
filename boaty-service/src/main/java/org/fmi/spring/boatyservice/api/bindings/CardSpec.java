package org.fmi.spring.boatyservice.api.bindings;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CardSpec {

    @NotEmpty
    public String cardHolder;
    @NotNull
    public Long cardNumber;
    @NotNull
    @NotEmpty
    public String validity;

    public YearMonth getValidity(DateTimeFormatter format) {
        return YearMonth.parse(validity, format);
    }
}
