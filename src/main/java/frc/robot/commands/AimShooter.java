package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.settings.Constants.Field;
import frc.robot.settings.Constants.ShooterConstants;
import frc.robot.subsystems.AngleShooterSubsystem;

public class AimShooter extends Command {
	AngleShooterSubsystem angleShooterSubsystem;
	DoubleSupplier POVSupplier;
	BooleanSupplier humanPlayerSupplier;
	BooleanSupplier SubwooferSupplier1;
	BooleanSupplier StageAngleSupplier;
	BooleanSupplier groundIntakeSup;
	BooleanSupplier farStageAngleSup;

	public AimShooter(AngleShooterSubsystem angleShooterSubsystem, DoubleSupplier POVSupplier, BooleanSupplier humanPlayerSupplier,
					  BooleanSupplier SubwooferSupplier1, BooleanSupplier StageAngleSupplier, BooleanSupplier groundIntakeSup, BooleanSupplier farStageAngleSup) {
		this.angleShooterSubsystem = angleShooterSubsystem;
		this.POVSupplier = POVSupplier;
		this.humanPlayerSupplier = humanPlayerSupplier;
		this.SubwooferSupplier1 = SubwooferSupplier1;
		this.StageAngleSupplier = StageAngleSupplier;
		this.groundIntakeSup = groundIntakeSup;
		this.farStageAngleSup = farStageAngleSup;
		addRequirements(angleShooterSubsystem);
	}

	@Override
	public void initialize() {

	}

	@Override
	public void execute() {
		if(SubwooferSupplier1.getAsBoolean()) {
			angleShooterSubsystem.setDesiredShooterAngle(Field.SUBWOOFER_ANGLE);
		} else if (StageAngleSupplier.getAsBoolean()) {
			angleShooterSubsystem.setDesiredShooterAngle(Field.PODIUM_SHOOTER_ANGLE);
		}  else if (farStageAngleSup.getAsBoolean()) {
			angleShooterSubsystem.setDesiredShooterAngle(Field.FAR_STAGE_SHOOTER_ANGLE);
		} else if (POVSupplier.getAsDouble() == 90 || POVSupplier.getAsDouble() == 45 || POVSupplier.getAsDouble() == 135) {
			angleShooterSubsystem.setDesiredShooterAngle(Field.AMPLIFIER_SHOOTER_ANGLE);
		} else if(humanPlayerSupplier.getAsBoolean()) {
			angleShooterSubsystem.setDesiredShooterAngle(ShooterConstants.HUMAN_PLAYER_ANGLE);
		} else if (groundIntakeSup.getAsBoolean()){
			angleShooterSubsystem.setDesiredShooterAngle(ShooterConstants.GROUND_INTAKE_SHOOTER_ANGLE);
		} else {
			angleShooterSubsystem.setDesiredShooterAngle(angleShooterSubsystem.calculateSpeakerAngle());
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(boolean interrupted) {
		angleShooterSubsystem.pitchShooter(0);
	}
}
