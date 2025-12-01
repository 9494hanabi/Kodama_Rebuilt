package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Servo;

public class ShootSubsystem extends SubsystemBase {
    private final SparkMax shootMotor_R = new SparkMax(1, MotorType.kBrushless);
    private final SparkMax shootMotor_L = new SparkMax(2, MotorType.kBrushless);
    private final Servo shootServo = new Servo(5);
    
    public void shoot(double leftpower, double rightpower) {
        shootMotor_R.set(leftpower);
        shootMotor_L.set(-rightpower);
    }

    public void shootStop() {
        shootMotor_R.set(0);
        shootMotor_L.set(0);
    }

    public void shootIntake() {
        shootMotor_R.set(-0.5);
        shootMotor_L.set(0.5);
    }

    public void setServoPosition() {
        shootServo.set(0.5); // サーボを特定の位置に設定（例: 100度）
    }
}
