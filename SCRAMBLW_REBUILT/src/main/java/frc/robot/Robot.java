package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.signals.InvertedValue;


/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  // ========================================モーターのピン========================================
  private static final int kFrontLeftChannel  = 2;
  private static final int kRearLeftChannel   = 3;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel  = 0;

  // ========================================コントローラーのピン========================================
  private static final int kJoystickChannel = 0;

  // ========================================ライブラリのインスタンス作成========================================
  private final MecanumDrive m_robotDrive;
  private final XboxController controller;

  /** Called once at the beginning of the robot program. */
  public Robot() {
    // ========================================モーターのインスタンス作成========================================
    TalonFX frontLeft = new TalonFX(kFrontLeftChannel);
    TalonFX rearLeft = new TalonFX(kRearLeftChannel);
    TalonFX frontRight = new TalonFX(kFrontRightChannel);
    TalonFX rearRight = new TalonFX(kRearRightChannel);

    // モーター反転の設定を行う
    MotorOutputConfigs motorOutput = new MotorOutputConfigs();
    motorOutput.Inverted = InvertedValue.Clockwise_Positive;

    // TalonFX の設定オブジェクトを取得して適用
    frontRight.getConfigurator().apply(motorOutput);
    rearRight.getConfigurator().apply(motorOutput);

    // ========================================メカナムドライブインスタンス作成========================================
    m_robotDrive = new MecanumDrive(frontLeft::set, rearLeft::set, frontRight::set, rearRight::set);

    controller = new XboxController(kJoystickChannel);

    SendableRegistry.addChild(m_robotDrive, frontLeft);
    SendableRegistry.addChild(m_robotDrive, rearLeft);
    SendableRegistry.addChild(m_robotDrive, frontRight);
    SendableRegistry.addChild(m_robotDrive, rearRight);
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.driveCartesian(
      -controller.getLeftY(), /* 前後 */
      -controller.getLeftX(), /* 左右 */
      -controller.getRightX() /* 回転 */
    );
  }
}
