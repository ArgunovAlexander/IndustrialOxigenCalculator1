package com.argunov.industrialoxigencalculator;

class CalcOxy {
    static final double OXYGEN_IN_AIR_CONC_BY_VOL=20.9;
    static final double OXYGEN_PURITY=99.5;
    //static final double OXYGEN_DENSITY=1.3315;
    //static final double AIR_DENSITY=1.2046;

    static double calcEnrichAirOxyConc(double air, double oxygen, double OxygenInAirByVol,
                                              double oxygenPurityByVol) {
        double oxygenInAir=air*OxygenInAirByVol/100; //returns quantity of oxygen in the same as air measure units
        double oxygenInOxygen=oxygen*oxygenPurityByVol/100; //returns quantity of oxygen in oxygen in the same as oxygen measure units
        return (oxygenInAir+oxygenInOxygen)/(air+oxygen)*100;//calculates enriched air oxygen concentration
    }
    static double calculateOxygenFlow (double air, double enrichAirOxyConcByVol, double oxygenInAirByVol,
                                                double oxygenPurityByVol) {
        return (air*(enrichAirOxyConcByVol-oxygenInAirByVol))/(oxygenPurityByVol-oxygenInAirByVol);
    }
    public static double calcDissipation (double air, double oxyFlow, double FurnaceOxyConcByVol,
                                          double oxygenInAirByVol, double oxygenPurityByVol) {
        System.out.println("******airFlow********"+ air+"\noxyFlow"+oxyFlow+ "\nFurnaceOxyConc"
                +FurnaceOxyConcByVol+"******"+oxyFlow*(oxygenPurityByVol-oxygenInAirByVol)/(FurnaceOxyConcByVol-oxygenInAirByVol));
                return oxyFlow*(oxygenPurityByVol-oxygenInAirByVol)/(FurnaceOxyConcByVol-oxygenInAirByVol)-air;
    }

}
