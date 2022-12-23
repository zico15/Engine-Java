package com.list;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class TextFieldNumber extends TextField {

    public TextFieldNumber(int value) {
        setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        setText(String.valueOf(value));
    }

    public TextFieldNumber(int value, Pos pos) {
        this(value);
        setAlignment(pos);
    }
}
