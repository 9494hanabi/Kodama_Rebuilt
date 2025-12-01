package frc.robot.subsystems;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShootAngleSubsystem extends SubsystemBase {
  private final SparkMax angleMotor = new SparkMax(1, MotorType.kBrushless);
  private final CANcoder angleEncoder = new CANcoder(0);


  private final double IntakePositon = 8;
  private final double ShootPositon = 70;
  private final double NomalPositon = 87;
  private final PIDController pidController = new PIDController(0.1, 0.0, 0.0);
  
  private double targetAngleDeg = 20.0;
  
  public void setIntakePositon() {
    targetAngleDeg = IntakePositon;
  }
  public void setShootPositon() { 
    targetAngleDeg = ShootPositon;
  }
  public void setNomalPositon() {
    targetAngleDeg = NomalPositon;
  }
  public double getCurrentAngleDeg() {
    double angle = angleEncoder.getAbsolutePosition().getValueAsDouble(); // 0.0〜1.0の範囲で取得 
    angle = angle * 360.0; // 0.0〜1.0を0.0〜360.0に変換
    return angle;
  }

  @Override
  public void periodic() {
    double currentAngle = getCurrentAngleDeg(); // 現在の角度を取得
    double output = pidController.calculate(currentAngle, targetAngleDeg); // 現在の角度を取得

    // 出力値を-1.0から1.0の範囲に制限
    if (output > 1.0) {
      output = 1.0;
    }else if (output < -1.0) {
      output = -1.0;
    }
    
    // 目標角度に近づいたら出力を0にする
    if (Math.abs(currentAngle - targetAngleDeg) < 1.5) { // 目標角度に近い場合は出力を0にする
      output = 0;
    }

    angleMotor.set(output); // モーターに出力を反映

    //値をSmartDashboardに表示
    SmartDashboard.putNumber("ShootAngleTarget", targetAngleDeg);
    SmartDashboard.putNumber("ShootAngleCurrent", currentAngle);
  }
}
