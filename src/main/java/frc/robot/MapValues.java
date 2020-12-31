/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * This is a basic number conversion class that we use for many FRC applications (joystick conversions mostly)
 * The constructor can take default limits on 
 */


public class MapValues {

    private Double defaultInputLowerLimit;
    private Double defaultInputUpperLimit;
    private double defaultOutputLowerLimit;
    private double defaultOutputUpperLimit;
    private double defaultInputRange;
    private double defaultOutputRange;

    MapValues(double inputLowerLimit, double inputUpperLimit, double outputLowerLimit, double outputUpperLimit){
        this.defaultInputLowerLimit = inputLowerLimit;
        this.defaultInputUpperLimit = inputUpperLimit;
        this.defaultOutputLowerLimit = outputLowerLimit;
        this.defaultOutputUpperLimit = outputUpperLimit;
        this.defaultInputRange = defaultInputUpperLimit-defaultInputLowerLimit;
        this.defaultOutputRange = defaultOutputUpperLimit-defaultOutputLowerLimit;
    }

    MapValues(double min, double max){
        this(min, max, 0,0);
    }

    MapValues(){
    }
    
    public double defaultRangeMapNumber(double input){
    double output;
            output = 0.0;
            if (!(this.defaultInputLowerLimit > 0x0)){ //0x0 is the minimum of a double value
                System.out.println("You have not instantiated the default input range. Please do so in the optional constructor or use mapNumber() instead.");
            }else if (!(this.defaultOutputLowerLimit > 0x0)){ //0x0 is the minimum of a double value
                System.out.println("You have not instantiated the default output range. Please use mapNumber() instead.");
            }
            else{
            output = (((input-this.defaultInputLowerLimit)/this.defaultInputRange)*this.defaultOutputRange)+this.defaultOutputLowerLimit;
            } 
        return output;		

    }

    public double mapNumber(double input,double inputLowerLimit, double inputUpperLimit, double outputLowerLimit, double outputUpperLimit){
        double output = 0;
        double inputRange = inputUpperLimit-inputLowerLimit;
        double outputRange = outputUpperLimit-outputLowerLimit;

        output = (((input-inputLowerLimit)/inputRange)*outputRange)+outputLowerLimit;
        return output;			
    }


}
