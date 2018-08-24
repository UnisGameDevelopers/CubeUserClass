package com.cube.user.impl;

import com.cube.user.type.UserClassType;
import com.cube.user.util.DataManager;

public abstract class AbstractClass {

    private static DataManager dataManager = new DataManager("class.yml");

    private int minValue, maxValue; /* 계급 수치 */
    private String className; /* 계급이름 */
    private UserClassType type;

    public AbstractClass(int minValue, int maxValue, String className) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.className = className;
    }

    public int getMinValue() { return minValue; }
    public int getMaxValue() { return maxValue; }
    public String getClassName() { return className; }
    public UserClassType getType() { return type; }
    public void setType(UserClassType type) { this.type = type; }
}
