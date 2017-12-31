package com.argunov.industrialoxigencalculator;

import android.content.Context;
import android.widget.Toast;


public class OxygenConcentration {
    public static final double IN_AIR_OXYGEN_CONCENTRATION=21;
    public static final double OXYGEN_PURENESS=99.5;
    public static final int MAX_AIR_FLOW=300000;
    public static final int MIN_AIR_FLOW=100000;
    public static final int MAX_OXYGEN_FLOW=30000;
    public static final int MIN_OXYGEN_FLOW=500;

    private double air=0;
    double oxygen = 0;

    protected double getOxygenConcentration(){
        return (air * IN_AIR_OXYGEN_CONCENTRATION + oxygen * OXYGEN_PURENESS) / (air + oxygen);
    }

    private  double checkedAirFlow(double airFlowDouble) {
        //checking air flow first
        if ((airFlowDouble <= MAX_AIR_FLOW / 60) && (airFlowDouble >= MIN_AIR_FLOW / 60)) {
        airFlowDouble *= 60;
        } else {
            if ((airFlowDouble <= MAX_AIR_FLOW / 1000) && (airFlowDouble >= MIN_AIR_FLOW / 1000)) {
                airFlowDouble *= 1000;
            } else {
                if ((airFlowDouble > MAX_AIR_FLOW) || (airFlowDouble < MIN_AIR_FLOW / 1000)) {
                } else {
                    if ((airFlowDouble > MAX_AIR_FLOW / 1000) && (airFlowDouble <= MIN_AIR_FLOW / 60)) {
                    } else {
                        if ((airFlowDouble > MAX_AIR_FLOW / 60) && (airFlowDouble <= MIN_AIR_FLOW)) {
                        }
                    }
                }
            }
        }
        return airFlowDouble;
    }

    private double checkedOxygenFlow(double oxygenFlowDouble) {
        //now checking oxygen flow
        if ((oxygenFlowDouble <= MAX_OXYGEN_FLOW / 1000) && (oxygenFlowDouble >= MIN_OXYGEN_FLOW / 1000)) {
            oxygenFlowDouble *= 1000;
        } else {
            if ((oxygenFlowDouble > MAX_OXYGEN_FLOW / 1000) && (oxygenFlowDouble <= MIN_OXYGEN_FLOW)) {
            }
        }
        return oxygenFlowDouble;
    }
}

