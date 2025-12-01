package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class DriveSubsystem extends SubsystemBase {
  private final PWMSparkMax leftMotor_0 = new PWMSparkMax(0);
  private final PWMSparkMax leftMotor_1 = new PWMSparkMax(1);

  private final PWMSparkMax rightMotor_0 = new PWMSparkMax(2);
  private final PWMSparkMax rightMotor_1 = new PWMSparkMax(3);

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

      leftMotor_0.set(left);
      leftMotor_1.set(-left);

      rightMotor_0.set(-right);
      rightMotor_1.set(right);
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
  
    /**
     * An example method querying a boolean state of the subsystem (for example, a digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     */
    public boolean exampleCondition() {
      // Query some boolean state, such as a digital sensor.
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