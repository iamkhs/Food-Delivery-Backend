package com.iamkhs.fooddelivery.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Custom validator for the @NotEmptyNotBlank annotation.
 * This validator checks if the provided CharSequence is not null, not empty, and not consisting solely of whitespace characters.
 */
public class NotEmptyNotBlankValidator implements ConstraintValidator<NotEmptyNotBlank, CharSequence> {

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The annotation instance.
     */
    @Override
    public void initialize(NotEmptyNotBlank constraintAnnotation) {
        // Initialization logic, if needed.
    }

    /**
     * Validates the provided value.
     *
     * @param value   The value to be validated.
     * @param context The validation context.
     * @return True if the value is valid; otherwise, false.
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        // Check if the value is null
        if (value == null) {
            return false; // Null values are considered invalid.
        }

        // Check if the value is a String
        if (value instanceof String) {
            // Trim the String to remove leading/trailing whitespace and check if it's empty or blank
            return !((String) value).trim().isEmpty() && !((String) value).trim().isBlank();
        }
        // For other types of CharSequence, we consider them valid
        return true;
    }
}
