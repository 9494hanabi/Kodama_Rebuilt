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

/**
 * このクラスはロボットの大部分を宣言する場所です。コマンドベースは「宣言型」パラダイムであるため、
 * {@link Robot} の定期メソッド内（スケジューラ呼び出しを除く）で実際に処理されるロボットロジックはごくわずかです。
 * 代わりに、ロボットの構造（サブシステム、コマンド、トリガーマッピングを含む）はここで宣言する必要があります。
 */
public class RobotContainer {
  // ロボットのサブシステムとコマンドはここに定義されています...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShootSubsystem m_shootSubsystem = new ShootSubsystem();
  private final ShootAngleSubsystem m_shootAngleSubsystem = new ShootAngleSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** ロボットのコンテナ。サブシステム、OIデバイス、およびコマンドを保持します。 */
  public RobotContainer() {
    // トリガーのバインディングを設定する
    configureBindings();
  }

  /**
   * このメソッドを使用して、トリガーとコマンドのマッピングを定義します。トリガーは、任意の述語を用いた
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} コンストラクタ、
   * または {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID} のサブクラスで定義される
   * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID} 向け {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} コントローラ、または {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick フライト
   * ジョイスティック} 用に定義された命名済みファクトリ経由で作成できます。
   */
  private void configureBindings() { //つまり、ボタン設定を書くところ バインディングって書いてある、、、ゲームで聞いたことあるね
    /*こっから足回りのキーバインド設定*/
    m_driveSubsystem.setDefaultCommand(
        new RunCommand(
            () -> m_driveSubsystem.arcadeDrive(
                -m_driverController.getLeftY(), //forward（前進、後進）
                m_driverController.getRightX() //rotation（回転）
            ),
            m_driveSubsystem));
    
    /*こっからシュートのキーバンド */
    m_shootSubsystem.setDefaultCommand( //shootSubsystemのキーバインド設定
        new RunCommand(
            () -> m_shootSubsystem.shoot(
                  m_driverController.getLeftTriggerAxis(),   // 左モーター
                  m_driverController.getRightTriggerAxis()   // 右モーター
            ), 
            m_shootSubsystem));

    /*こっからシュートアングルのキーバインド */
    // Aボタンでインテーク位置に移動
    m_driverController.a().onTrue(
    new InstantCommand(m_shootAngleSubsystem::setIntakePositon, m_shootAngleSubsystem)
    );
    // Bボタンでシュート位置に移動
    m_driverController.b().onTrue(
    new InstantCommand(m_shootAngleSubsystem::setShootPositon, m_shootAngleSubsystem)
    );
    // Xボタンで通常位置に戻る
    m_driverController.x().onTrue(
    new InstantCommand(m_shootAngleSubsystem::setNomalPositon, m_shootAngleSubsystem)
    );
  }

  /**
   * これを用いて自律モードのコマンドをメインの{@link Robot}クラスに渡します。
   *
   * @return 自律モードで実行するコマンド
   */
  // public Command getAutonomousCommand() {
  //   // 例示コマンドは自律モードで実行されます
  //   return Autos.exampleAuto(m_driveSubsystem);
  // }
}
