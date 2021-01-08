/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

import edu.wpi.first.wpilibj.simulation.SimDeviceSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Sensors.RomiGyro;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.RomiDrivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
 
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final RomiDrivetrain m_drivetrain = new RomiDrivetrain();
  private final Arm m_arm = new Arm();
  private final XboxController m_controller = new XboxController(0);
  //private double m_mappedValue;
  private static MapValues m_tiltValue = new MapValues(-1,1,0,1);
  private static MapValues m_liftValue = new MapValues(-1,1,0,1);
  //private static DigitalOutput m_greenLed = new DigitalOutput(8);
  //private Spark m_motor = new Spark(4);
  //private AnalogInput m_leftIR = new AnalogInput(0);
  //private AnalogInput m_rightIR = new AnalogInput(1);
  //private AnalogInput m_servoFeedback = new AnalogInput(0);
  //private static Ultrasonic m_ultrasonic = new Ultrasonic(8,9);
  
  // Creates an object for the built-in accelerometer
  // Range defaults to +- 8 G's
  Accelerometer m_accelerometer = new BuiltInAccelerometer();

  private DigitalInput ultraOut = new DigitalInput(8);
  private DigitalOutput ultraIn = new DigitalOutput(9);
  private AnalogInput voltage = new AnalogInput(0);

  private RomiGyro m_gyro = new RomiGyro();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    // Starts the ultrasonic sensor running in automatic mode
    //m_ultrasonic.setAutomaticMode(true);
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    var batteryVoltage = RobotController.getBatteryVoltage();
    //SmartDashboard.putNumber("converted Value", mappedValue);
    SmartDashboard.putData("Auto choices", m_chooser);
    //SmartDashboard.putNumber("left voltage", m_leftIR.getVoltage());
    //SmartDashboard.putNumber("right voltage", m_rightIR.getValue());
    //SmartDashboard.putNumber("servoFeedback", m_servoFeedback.getVoltage());
    //SmartDashboard.putBoolean("button", m_button.get());
    //SmartDashboard.putNumber("Distance", m_ultrasonic.getRangeInches());
    SmartDashboard.putNumber("Accelerometer X", m_accelerometer.getX());
    SmartDashboard.putNumber("Accelerometer Y",  m_accelerometer.getY());
    SmartDashboard.putNumber("leftEncoderSPeed", m_drivetrain.getRightEncoderSpeed());
    SmartDashboard.putBoolean("Right Wheel is moving", m_drivetrain.getRightEncoderStopped());
    SmartDashboard.putNumber("battery", batteryVoltage);
    SmartDashboard.putBoolean("UltraDistance", ultraOut.get());
    SmartDashboard.putNumber("Voltage", voltage.getAverageVoltage());

    //Gyro 
    SmartDashboard.putNumber("Gyro X",m_gyro.getAngleX());
    SmartDashboard.putNumber("Gyro X",m_gyro.getAngleY());
    SmartDashboard.putNumber("Gyro X",m_gyro.getAngleZ());
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() { 
    //m_arm.setClawJaw(.5);
    //m_arm.setClawTiltAngle(.5);
   // m_arm.setLift(.5);
   //m_ultrasonic.ping();
 


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    m_drivetrain.arcadeDrive(0, 0);
    ultraIn.set(true);

    
    
  }

  /**
   * This function is called once when teleop is enabled.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */

  @Override
  public void teleopPeriodic() {
    m_drivetrain.arcadeDrive(m_controller.getX(Hand.kLeft),-m_controller.getY(Hand.kLeft));
   
    m_arm.setLift(m_liftValue.defaultRangeMapNumber(-m_controller.getY(Hand.kRight)));

    m_arm.setClawTiltAngle(m_tiltValue.defaultRangeMapNumber(-m_controller.getX(Hand.kRight)));
    if (m_controller.getRawButton(2)){
      m_arm.setClawJaw(1);
    } else if (m_controller.getRawButton(3)){
      m_arm.setClawJaw(0);
    }
    
    //motor.set(m_controller.getX(Hand.kRight));
    //m_drivetrain.arcadeDrive(0,0);
    // m_greenLed.set(true);
  }

  /**
   * This function is called once when the robot is disabled.
   */

  @Override
  public void disabledInit() {
  }

  /**
   * This function is called periodically when disabled.
   */
  @Override
  public void disabledPeriodic() {
    ultraIn.set(false);
  }

  /**
   * This function is called once when test mode is enabled.
   */
  @Override
  public void testInit() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
