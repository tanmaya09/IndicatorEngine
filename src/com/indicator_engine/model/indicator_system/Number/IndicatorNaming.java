package com.indicator_engine.model.indicator_system.Number;

import java.io.Serializable;

/**
 * Created by Tanmaya Mahapatra on 31-03-2015.
 */
@SuppressWarnings({"unused", "unchecked"})
public class IndicatorNaming implements Serializable{
    private String indicatorName;

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }
}