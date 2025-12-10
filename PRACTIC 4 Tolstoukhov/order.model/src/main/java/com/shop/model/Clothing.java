package com.shop.model;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class Clothing extends Product {
    private final String size;
    private final String material;
}