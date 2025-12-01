package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Commands.DriveCommand;
import java.util.function.DoubleSupplier;

public class RobotContainer {
  // ==================================================メソッド/クラスのインスタンス作成==================================================
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  // ==================================================パーツ定義==================================================
  private final XboxController controller = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();

    // categoly : explain
    // id       : 01
    // moveSupplier
    // rotateSupplier
    // -> DriveCommandで使うコントローラー値の取得を抽象化するための関数を定義している。
    DoubleSupplier moveSupplier = () -> {
      double val = -controller.getLeftY();
      return val;
    };
    DoubleSupplier rotateSupplier = () -> {
      double val = controller.getRightX();
      return val;
    };

    driveSubsystem.setDefaultCommand(
      new DriveCommand(driveSubsystem, moveSupplier, rotateSupplier)
    );
  }

  private void configureButtonBindings() {

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
