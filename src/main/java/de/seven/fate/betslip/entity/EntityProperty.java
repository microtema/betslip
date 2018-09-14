package de.seven.fate.betslip.entity;

import lombok.Value;

@Value
public class EntityProperty<T> {

    private final String propertyName;

    private final T propertyValue;

    private final T recentPropertyValue;
}
