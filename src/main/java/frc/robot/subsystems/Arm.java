/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;


/**
 * This is my implementation of the Romi Arm module. We could probably recreate this relatively easily with Vex products.
 */

public class Arm {
  private static final Servo m_armLift = new Servo(2);
  private static final Servo m_clawTilt = new Servo(3);
  private static final Servo m_clawJaw = new Servo(4);
  //private static final PWMVictorSPX m_armMotor = new PWMVictorSPX(4);
  

  //private static final PWM m_setupPWM = new PWM(5);


  /**public void setTestPWM(int setValue){
    m_setupPWM.setRaw(setValue);
    
  }*/
  public void setLift(double liftAngle){
    m_armLift.set(liftAngle);
  }
  public void setClawTiltAngle(double clawTiltAngle){
    m_clawTilt.set(clawTiltAngle);

  }
 public void setClawJaw(double clawJaw){
    m_clawJaw.set(clawJaw);

  }
  /**public void setArmMotor(double motorSpeed){
    m_armMotor.set(motorSpeed);
  }*/
  
}
