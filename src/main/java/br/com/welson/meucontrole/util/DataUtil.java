package br.com.welson.meucontrole.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public abstract class DataUtil {

    public static LocalDate getUltimoDiaMesAtual() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getPrimeiroDiaMesAtual() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getUltimoDiaMes(Month mes, int ano) {
        return getLocalDate(mes, ano).with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getPrimeiroDiaMes(Month mes, int ano) {
        return getLocalDate(mes, ano).with(TemporalAdjusters.firstDayOfMonth());
    }

    private static LocalDate getLocalDate(Month mes, int ano) {
        return LocalDate.now().withMonth(mes.getValue()).withYear(ano);
    }
}
