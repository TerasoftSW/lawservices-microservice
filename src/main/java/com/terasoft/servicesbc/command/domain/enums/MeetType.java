package com.terasoft.servicesbc.command.domain.enums;

public enum MeetType {
    FACETOFACE(0),
    VIRTUAL(1);

    private final int value;

    MeetType(final int state) { value = state; }

    public int getValue() { return value; }
}
