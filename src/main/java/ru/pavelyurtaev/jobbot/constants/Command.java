package ru.pavelyurtaev.jobbot.constants;

public enum Command {
    START("/start");

    public final String text;

    Command(String text) {
        this.text = text;
    }
}
