package com.shop.model;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class Electronics extends Product {
    private final int warrantyPeriodMonths;
}