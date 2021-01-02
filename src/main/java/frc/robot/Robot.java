/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Sendable;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.SimDeviceSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  private static MapValues m_fixedValue = new MapValues();
  //private Spark m_motor = new Spark(4);
  //private AnalogInput m_leftIR = new AnalogInput(0);
  //private AnalogInput m_rightIR = new AnalogInput(1);
  //private AnalogInput m_servoFeedback = new AnalogInput(2);
  Ultrasonic m_ultrasonic = new Ultrasonic(10,11);
  // Creates an object for the built-in accelerometer
  // Range defaults to +- 8 G's
  Accelerometer m_accelerometer = new BuiltInAccelerometer();

  private AnalogGyro m_gyro = new AnalogGyro(0);

  // Create the simulated gyro object, used for setting the gyro
  // angle. Like EncoderSim, this does not need to be commented out
  // when deploying code to the roboRIO.
  private AnalogGyroSim m_gyroSim = new AnalogGyroSim(m_gyro);






  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    

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
    //SmartDashboard.putNumber("converted Value", mappedValue);
    SmartDashboard.putData("Auto choices", m_chooser);
    //SmartDashboard.putNumber("left voltage", m_leftIR.getVoltage());
    //SmartDashboard.putNumber("right voltage", m_rightIR.getValue());
    //SmartDashboard.putNumber("servoFeedback", m_servoFeedback.getVoltage());
    //SmartDashboard.putBoolean("button", m_button.get());
    SmartDashboard.putNumber("Distance", m_ultrasonic.getRangeInches());
    SmartDashboard.putNumber("Accelerometer X", m_accelerometer.getX());
    //SmartDashboard.putNumber("Accelerometer Y",  m_accelerometer.getY());
    SmartDashboard.putNumber("gyro", m_gyro.getAngle());
    SmartDashboard.putNumber("leftEncoderSPeed", m_drivetrain.getRightEncoderSpeed());
    SmartDashboard.putBoolean("Right Wheel is moving", m_drivetrain.getRightEncoderStopped());
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
 


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
    
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
    m_drivetrain.arcadeDrive(m_controller.getY(Hand.kLeft),m_controller.getX(Hand.kLeft));
    //double liftValue = fixedValue.m_mapNumber(m_controller.getY(Hand.kRight), -1.0, 1.0, 0.0, 1.0);
    //m_arm.setLift(m_liftValue);
    //m_arm.setClawTiltAngle(m_liftValue);
    //m_arm.setArmMotor(m_controller.getX(Hand.kRight ));
    //motor.set(m_controller.getX(Hand.kRight));
    //m_drivetrain.arcadeDrive(0,0);
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
