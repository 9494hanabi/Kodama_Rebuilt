package frc.robot.subsystems;

//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class ShootSubsystem extends SubsystemBase {
    private final PWMSparkMax shootMotor_0 = new PWMSparkMax(4);
    private final PWMSparkMax shootMotor_1 = new PWMSparkMax(5);

    //private final PWMsim angleMotor = new PWMsim(6);
    
    public void shoot(double leftpower, double rightpower) {
        shootMotor_0.set(leftpower);
        shootMotor_1.set(-rightpower);
    }

    public void shootStop() {
        shootMotor_0.set(0);
        shootMotor_1.set(0);
    }

    public void shootReverse() {
        
    }
}
