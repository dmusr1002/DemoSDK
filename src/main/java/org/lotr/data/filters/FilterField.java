package org.lotr.data.filters;

public enum FilterField {
    NAME("name"),
    RUNTIME_IN_MINUTES("runtimeInMinutes"),
    BUDGET_IN_MILLIONS("budgetInMillions"),
    BOX_OFFICE_REVENUE_IN_MILLIONS("boxOfficeRevenueInMillions"),
    ACADEMY_AWARD_NOMINATIONS("academyAwardNominations"),
    ACADEMY_AWARD_WINS("academyAwardWins"),
    ROTTEN_TOMATOES_SCORE("rottenTomatoesScore"),
    DIALOG("dialog"),
    MOVIE("movie"),
    CHARACTER("character");

    private final String fieldName;

    FilterField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
