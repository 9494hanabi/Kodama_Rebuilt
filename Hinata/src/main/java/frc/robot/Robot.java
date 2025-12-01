// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  // ========================================モーターコントローラー========================================
  private final PWMSparkMax m_leftLeader = new PWMSparkMax(2);
  private final PWMSparkMax m_rightLeader = new PWMSparkMax(4);
  private final PWMSparkMax m_leftFollower = new PWMSparkMax(3);
  private final PWMSparkMax m_rightFollower = new PWMSparkMax(5);
  private final PWMSparkMax m_Servo = new PWMSparkMax(6);


  // ========================================インスタンス作成========================================
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftLeader::set, m_rightLeader::set);

  // ========================================コントローラー========================================
  private final XboxController controller = new XboxController(0);

  // ========================================エンコーダー========================================
  // DIO 0,1 に接続したMag Encoderを読む
  private final Encoder magEncoder = new Encoder(0, 1);

  // 例: 1回転4096カウント, ギア比 10:1, タイヤ直径 0.1524m(6inch)
  private static final double PULSES_PER_ROTATION = 4096.0;
  private static final double GEAR_RATIO = 10.0; // モータ10回転でホイール1回転
  private static final double WHEEL_DIAMETER_M = 0.1524;

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftLeader);
    SendableRegistry.addChild(m_robotDrive, m_rightLeader);

    m_leftLeader.addFollower(m_leftFollower);
    m_rightLeader.addFollower(m_rightFollower);
    

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightLeader.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-controller.getLeftY(), -controller.getRightX());
    
  }
}
