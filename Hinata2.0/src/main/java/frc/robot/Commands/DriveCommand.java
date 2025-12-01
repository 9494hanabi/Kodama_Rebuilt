package frc.robot.Commands;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

public class DriveCommand extends Command {

    // ==================================================メソッド/クラスのインスタンス作成==================================================
    private final DriveSubsystem driveSubsystem;
    private final DoubleSupplier moveSupplier;
    private final DoubleSupplier rotateSupplier;

    // ==================================================初期化==================================================
    public DriveCommand(
        DriveSubsystem driveSubsystem,

        // categoly : explain
        // id       : 01
        DoubleSupplier moveSupplier,
        DoubleSupplier rotateSupplier) {
        this.driveSubsystem = driveSubsystem;
        this.moveSupplier   = moveSupplier;
        this.rotateSupplier = rotateSupplier;
        addRequirements(driveSubsystem);
    }

    // ==================================================周期実行==================================================
    @Override
    public void execute() {
        // categoly : explain
        // id       : 01
        // moveSupplier
        // rotateSupplier
        // -> コントローラーの値を取得するλ式が格納されている。
        double moveSpeed   = moveSupplier.getAsDouble();
        double rotateSpeed = rotateSupplier.getAsDouble();

        driveSubsystem.arcadeDrive(moveSpeed, rotateSpeed);
    }

    // ==================================================終了処理==================================================
    @Override
    public void end(boolean interrupted) {
        driveSubsystem.arcadeDrive(0, 0);
    }

    // ==================================================終了条件==================================================
    // ↓手動操作なので終了しない。
    @Override
    public boolean isFinished() {
        return false;
    }
}
