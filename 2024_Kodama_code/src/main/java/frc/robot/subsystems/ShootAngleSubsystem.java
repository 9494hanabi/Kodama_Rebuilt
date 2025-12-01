package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.math.controller.PIDController;

public class ShootAngleSubsystem extends SubsystemBase {
  private final Spark angleMotor = new Spark(6);
  private final CANcoder angleEncoder = new CANcoder(0);


  private final double IntakePositon = 20.0; // インテークの角度、例: 20度
  private final double ShootPositon = 60.0; // シュートの角度、例: 60度
  private final double NomalPositon = 40.0; // 通常位置の角度、例: 40度
  private final PIDController pidController = new PIDController(0.1, 0.0, 0.0); // PID制御器のパラメータ
  
  private double targetAngleDeg = 20.0; // 目標角度、例: 20度
  
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
