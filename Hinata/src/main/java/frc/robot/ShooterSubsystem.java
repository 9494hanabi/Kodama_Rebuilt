package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterSubsystem {
    private final WPI_TalonSRX shooter = new WPI_TalonSRX(1);

    // 1回転あたり4096カウント
    private static final int TICKS_PER_REV = 4096;

    public ShooterSubsystem() {
        shooter.configFactoryDefault();

        // フィードバックセンサとしてMag Encoder(相対)を選択
        shooter.configSelectedFeedbackSensor(
            FeedbackDevice.CTRE_MagEncoder_Relative,
            0,      // PIDスロット
            30      // タイムアウトms
        );

        // センサの向きがモータと同じになるように調整（回転させて確認）
        shooter.setSensorPhase(true);

        // モータの正転方向
        shooter.setInverted(false);

        // 簡易PIDゲイン（例）
        shooter.config_kP(0, 0.1);
        shooter.config_kI(0, 0.0);
        shooter.config_kD(0, 0.0);
        shooter.config_kF(0, 0.05);
    }

    // RPM → Talonの「ticks/100ms」単位へ変換
    private double rpmToTicksPer100ms(double rpm) {
        double revPer100ms = rpm / 600.0; // 600 = 60s / 0.1s
        return revPer100ms * TICKS_PER_REV;
    }

    public void setShooterRpm(double targetRpm) {
        double targetTicksPer100ms = rpmToTicksPer100ms(targetRpm);
        shooter.set(ControlMode.Velocity, targetTicksPer100ms);
    }

    public double getShooterRpm() {
        double sensorVel = shooter.getSelectedSensorVelocity(); // ticks/100ms
        double revPerSec = (sensorVel / TICKS_PER_REV) * 10.0;  // 10 = 1s / 0.1s
        return revPerSec * 60.0;
    }
}