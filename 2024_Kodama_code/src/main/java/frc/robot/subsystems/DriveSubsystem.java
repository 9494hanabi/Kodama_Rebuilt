package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class DriveSubsystem extends SubsystemBase {
  private final PWMSparkMax leftMotor_f = new PWMSparkMax(2);
  private final PWMSparkMax leftMotor_b = new PWMSparkMax(3);

  private final PWMSparkMax rightMotor_f = new PWMSparkMax(4);
  private final PWMSparkMax rightMotor_b = new PWMSparkMax(5);

  /** Creates a new DriveSubsystem. */
  public void arcadeDrive(double forward, double rotation) {
    double left = forward + rotation;
    double right = forward - rotation;

    if (left > 1.0) {
      left = 1.0;
    } else if (left < -1.0) {
      left = -1.0;
    }
    if (right > 1.0) {
      right = 1.0;
    } else if (right < -1.0) {
      right = -1.0;
    }

    leftMotor_f.set(-left);
    leftMotor_b.set(-left);

    rightMotor_f.set(right);
    rightMotor_b.set(right);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public boolean exampleCondition() {
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

  