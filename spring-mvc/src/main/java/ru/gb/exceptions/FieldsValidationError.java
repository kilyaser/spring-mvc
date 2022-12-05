package ru.gb.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FieldsValidationError {
    private List<String> errorFieldMessages;

    public FieldsValidationError(List<String> errorFieldMessages) {
        this.errorFieldMessages = errorFieldMessages;
    }
}
