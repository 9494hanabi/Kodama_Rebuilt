package frc.robot.Subsystems;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSubsystem extends SubsystemBase {
    // ==================================================パーツ定義==================================================
    private final PWMSparkMax leftFrontMotor  = new PWMSparkMax(0);
    private final PWMSparkMax leftBackMotor   = new PWMSparkMax(2);
    private final PWMSparkMax rightFrontMotor = new PWMSparkMax(1);
    private final PWMSparkMax rightBackMotor  = new PWMSparkMax(3);

    // ==================================================メソッド/クラスのインスタンス作成==================================================
    private final DifferentialDrive drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    // ==================================================初期化==================================================
    public DriveSubsystem() {
        leftFrontMotor.setInverted(false);
        rightFrontMotor.setInverted(true);
        leftFrontMotor.addFollower(leftBackMotor);
        rightFrontMotor.addFollower(rightBackMotor);
    }

    // ==================================================実行する処理==================================================
    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        drive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    @Override
    public void periodic() {
        
    }
}