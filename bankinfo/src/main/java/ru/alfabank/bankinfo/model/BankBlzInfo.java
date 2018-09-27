package ru.alfabank.bankinfo.model;

import lombok.Data;

@Data
public class BankBlzInfo {
    String bezeichnung;
    String bic;
    String ort;
    String plz;
}
