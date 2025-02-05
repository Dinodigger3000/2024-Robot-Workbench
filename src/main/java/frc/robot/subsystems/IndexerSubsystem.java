package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.SparkAnalogSensor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.settings.Constants.IndexerConstants;

public class IndexerSubsystem extends SubsystemBase {
    TalonFX m_IndexerMotor;
    SparkAnalogSensor m_DistanceSensor;
    CurrentLimitsConfigs currentLimitsConfigs;
    TalonFXConfigurator configurator;

    public IndexerSubsystem() {
        m_IndexerMotor = new TalonFX(IndexerConstants.INDEXER_MOTOR);
        m_IndexerMotor.setInverted(false);

        currentLimitsConfigs = new CurrentLimitsConfigs();
        currentLimitsConfigs.SupplyCurrentLimit = IndexerConstants.CURRENT_LIMIT;
        currentLimitsConfigs.SupplyCurrentLimitEnable = true;
        configurator = m_IndexerMotor.getConfigurator();
        configurator.apply(currentLimitsConfigs);
    }

    public void on() {
        m_IndexerMotor.set(IndexerConstants.INDEXER_INTAKE_SPEED);
    }

    public void off() {
        m_IndexerMotor.set(0);
    }

    public void reverse() {
        m_IndexerMotor.set(-IndexerConstants.INDEXER_INTAKE_SPEED);
    }
    public void set(double speed) {
        m_IndexerMotor.set(speed);
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("indexer current", m_IndexerMotor.getSupplyCurrent().getValueAsDouble());
    }
}
