/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RomiDrivetrain {
  /**This is a helpful Romi Setup. Many helpful constants are here.
   * They come courtesy of the WPILib2020 Hour of Code and Romi Reveal simulation. 
   * Find it here https://www.youtube.com/watch?v=W3hX-cEsVwM 
   */
  
  private static final double kWheelDiameterInch = 2.75;
  private static final double kPulsesPerRevolution = 1440.0; //The template calls this counts per revolution. In this case, they are the same.
  public static final double kInchesPerPulse = Math.PI * kWheelDiameterInch / kPulsesPerRevolution;
  public static final double kWheelTrack = 5.25; // 5.86;
  public static final double kInchesPerDegree = Math.PI * kWheelDiameterInch / 360;
  private static final double kDistancePerPulseInches = kWheelDiameterInch/kPulsesPerRevolution;

  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  private final Spark m_leftMotor = new Spark(0);
  private final Spark m_rightMotor = new Spark(1);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);
 

  // Set up the differential drive controller
  private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  /**
   * Creates a new RomiDrivetrain.
   */
  public RomiDrivetrain() {
    // DifferentialDrive defaults to having the right side flipped
    // We don't need to do this because the Romi has accounted for this
    // in firmware/hardware
    m_diffDrive.setRightSideInverted(false);
    m_leftEncoder.setDistancePerPulse(kDistancePerPulseInches);
    m_rightEncoder.setDistancePerPulse(kDistancePerPulseInches);
    resetEncoders();
  }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    m_diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public int getLeftEncoderCount() {
    return m_leftEncoder.get();
  }

  public int getRightEncoderCount() {
    return m_rightEncoder.get();
  }

  public double getLeftDistanceInch() {
    return Math.PI * kWheelDiameterInch * (getLeftEncoderCount() / kPulsesPerRevolution);
  }

  public double getRightDistanceInch() {
    return Math.PI * kWheelDiameterInch * (getRightEncoderCount() / kPulsesPerRevolution);
  }

  public double getRightEncoderSpeed() {
    return m_rightEncoder.getRate();
  }
  public boolean getRightEncoderStopped() {
    return m_rightEncoder.getStopped();
  }

}
