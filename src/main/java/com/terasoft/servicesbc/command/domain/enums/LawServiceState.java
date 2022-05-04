package com.terasoft.servicesbc.command.domain.enums;

public enum LawServiceState {
    INPROCESS (1),
    FINISHED(2);

    private final int value;

    LawServiceState(final int state) { value = state; }

    public int getValue() { return value; }

}
