// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand; //RunCommandクラスのインポート
import edu.wpi.first.wpilibj2.command.button.CommandXboxController; //Xboxコントローラー用のコマンドクラス
import edu.wpi.first.wpilibj2.command.button.Trigger;
//import edu.wpi.first.wpilibj2.command.button.Trigger; //Triggerクラス
import frc.robot.Constants.OperatorConstants; //OperatorConstantsのインポート

/*Subsystemのimport→sybststemsフォルダ内のDriveSubsystemをインポート*/
import frc.robot.subsystems.DriveSubsystem; //DriveSubsystemのインポート
import frc.robot.subsystems.ShootSubsystem; //ShootSubsystemのインポート
import frc.robot.subsystems.ShootAngleSubsystem; //ShootAngleSubsystemのインポート

public class RobotContainer {
  // ロボットのサブシステムとコマンドはここに定義されています...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // ==========================================シュートサブシステムのインスタンス作成==========================================
  private final ShootSubsystem m_shootSubsystem = new ShootSubsystem();
  private final ShootAngleSubsystem m_shootAngleSubsystem = new ShootAngleSubsystem();

  // ==========================================Xboxコントローラーのインスタンス作成==========================================
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() { //つまり、ボタン設定を書くところ バインディングって書いてある、、、ゲームで聞いたことあるね
    /*こっから足回りのキーバインド設定*/
    m_driveSubsystem.setDefaultCommand(
        new RunCommand(
            () -> m_driveSubsystem.arcadeDrive(
                m_driverController.getLeftY(), //forward（前進、後進）
                m_driverController.getRightX() //rotation（回転）
            ),
            m_driveSubsystem));
    
    /*こっからシュートのキーバンド */
    m_shootSubsystem.setDefaultCommand(
        new RunCommand(
            () -> m_shootSubsystem.shoot(
                  m_driverController.getLeftTriggerAxis(),   // 左モーター
                  m_driverController.getRightTriggerAxis()   // 右モーター
            ),
            m_shootSubsystem));
    
    // Yボタンでサーボを動かす
    m_driverController.y().onTrue(
    new InstantCommand(m_shootSubsystem::setServoPosition, m_shootSubsystem)
    );

    /*こっからシュートアングルのキーバインド */
    // Aボタンでインテーク位置に移動
    m_driverController.a().onTrue(
    new InstantCommand(
            () -> {
                  m_shootAngleSubsystem.setIntakePositon();
                  m_shootSubsystem.shootIntake(); // ← 追加したい処理
            },
            m_shootAngleSubsystem));
    
    // Bボタンでシュート位置に移動
    m_driverController.b().onTrue(
    new InstantCommand(m_shootAngleSubsystem::setShootPositon, m_shootAngleSubsystem)
    );
    // Xボタンで通常位置に戻る
    m_driverController.x().onTrue(
    new InstantCommand(m_shootAngleSubsystem::setNomalPositon, m_shootAngleSubsystem)
    );
  }
}
