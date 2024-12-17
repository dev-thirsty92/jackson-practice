package com.practice.custom;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonSerialize(using = CustomItemSerializer.class)
public class Item {
    public int id;
    public String name;
    public User owner;

}
