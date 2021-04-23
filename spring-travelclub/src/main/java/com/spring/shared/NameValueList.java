package com.spring.shared;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameValueList {
    //
    private List<NameValue> nameValues;

    public NameValueList() {
        //
        this.nameValues = new ArrayList<>();
    }

    public void addNameValue(String name, String value) {
        //
        NameValue nameValue = new NameValue(name, value);
        nameValues.add(nameValue);
    }
}
