package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  // ==================================================メソッド/クラスのインスタンス作成==================================================
  private Command m_autonomousCommand;
  private RobotContainer robotContainer;

  @Override
  public void robotInit() {
    // ==================================================RobotContainer作成==================================================
    robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    // ==================================================周期処理を実行==================================================
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    // ==================================================Autonomous開始時の初期化関数==================================================
    m_autonomousCommand = robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void teleopInit() {
    // ==================================================手動操作開始時の初期化関数==================================================
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void disabledInit() {
    // ==================================================diable時の初期化関数==================================================
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void testInit() {
    // ==================================================test時の初期化関数==================================================
    CommandScheduler.getInstance().cancelAll();
  }
}