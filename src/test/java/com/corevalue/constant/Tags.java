package com.corevalue.constant;

public enum Tags {
    BUTTON, UL, LI, A, DIV;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
