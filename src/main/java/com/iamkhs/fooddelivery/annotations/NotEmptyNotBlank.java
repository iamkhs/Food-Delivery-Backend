package com.iamkhs.fooddelivery.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * Custom validation annotation that combines the checks for non-null, non-empty, and non-blank values of a CharSequence.
 */
@Documented
@Constraint(validatedBy = {NotEmptyNotBlankValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NotEmptyNotBlank.List.class)
@ReportAsSingleViolation
public @interface NotEmptyNotBlank {

    /**
     * The error message to be used when the validation fails.
     *
     * @return The error message.
     */
    String message() default "{This field must not be empty or null or contain only whitespace characters}";

    /**
     * Groups for grouping related constraints. Not used in this annotation by default.
     *
     * @return An array of groups.
     */
    Class<?>[] groups() default {};

    /**
     * Payloads for extending validation behavior. Not used in this annotation by default.
     *
     * @return An array of payloads.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines a list of {@code @NotEmptyNotBlank} annotations for repeatable usage.
     */
    @Documented
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        NotEmptyNotBlank[] value();
    }
}
