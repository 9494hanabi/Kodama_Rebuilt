package frc.robot.subsystems;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveSubsystem extends SubsystemBase {
  // ==================================================パーツ定義==================================================
  private final PWMSparkMax leftLeader = new PWMSparkMax(2);
  private final PWMSparkMax leftFollower = new PWMSparkMax(3);
  private final PWMSparkMax rightLeader = new PWMSparkMax(4);
  private final PWMSparkMax rightFollower = new PWMSparkMax(5);


  // ==================================================メソッド/クラスのインスタンス作成==================================================
  private final DifferentialDrive drive = new DifferentialDrive(leftLeader, rightLeader);


  // ==================================================メソッド/クラスのインスタンス作成==================================================
  private final double speedScaler = 0.5;


  // ==================================================初期化==================================================
  public DriveSubsystem() {
    leftLeader.addFollower(leftFollower);
    rightLeader.addFollower(rightFollower);
  }


  // ==================================================実行する処理==================================================
  public void arcadeDrive(double forward, double rotation) {
    drive.arcadeDrive(forward * speedScaler, rotation * speedScaler);
  }


  public Command exampleMethodCommand() {
    return runOnce(
        () -> {
        });
  }


  public boolean exampleCondition() {
    return false;
  }


  @Override
  public void periodic() {
  }

  
  @Override
  public void simulationPeriodic() {
  }
}

  