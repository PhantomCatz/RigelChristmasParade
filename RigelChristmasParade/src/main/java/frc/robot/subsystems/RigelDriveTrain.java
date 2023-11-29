// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RigelDriveTrain extends SubsystemBase {
  private static RigelDriveTrain instance;
  
  private CANSparkMax sparkLtFront;
  private CANSparkMax sparkLtMiddle;
  private CANSparkMax sparkLtBack;

  private CANSparkMax sparkRtFront;
  private CANSparkMax sparkRtMiddle;
  private CANSparkMax sparkRtBack;

  private final double DEADBAND = 0.04;
  private final int CURRENT_LIMIT = 55;

  private final int SPARK_LT_FRONT_ID = 1;
  private final int SPARK_LT_MIDDLE_ID = 2;
  private final int SPARK_LT_BACK_ID = 3;

  private final int SPARK_RT_FRONT_ID = 4;
  private final int SPARK_RT_MIDDLE_ID = 5;
  private final int SPARK_RT_BACK_ID = 6;

  private MotorControllerGroup leftMotors;
  private MotorControllerGroup rightMotors;

  public DifferentialDrive rigelDriveTrain;

  private RigelDriveTrain() {
    sparkLtFront = new CANSparkMax(SPARK_LT_FRONT_ID, MotorType.kBrushless);
    sparkLtMiddle = new CANSparkMax(SPARK_LT_MIDDLE_ID, MotorType.kBrushless);
    sparkLtBack = new CANSparkMax(SPARK_LT_BACK_ID, MotorType.kBrushless);

    sparkRtMiddle = new CANSparkMax(SPARK_RT_MIDDLE_ID, MotorType.kBrushless);
    sparkRtFront = new CANSparkMax(SPARK_RT_FRONT_ID, MotorType.kBrushless);
    sparkRtBack = new CANSparkMax(SPARK_RT_BACK_ID, MotorType.kBrushless);

    sparkLtFront.setInverted(true);
    sparkLtMiddle.setInverted(true);
    sparkLtBack.setInverted(true);

    sparkLtFront.setSmartCurrentLimit(CURRENT_LIMIT);
    sparkLtMiddle.setSmartCurrentLimit(CURRENT_LIMIT);
    sparkLtBack.setSmartCurrentLimit(CURRENT_LIMIT);
    
    sparkRtFront.setSmartCurrentLimit(CURRENT_LIMIT);
    sparkRtMiddle.setSmartCurrentLimit(CURRENT_LIMIT);
    sparkRtBack.setSmartCurrentLimit(CURRENT_LIMIT);

    leftMotors = new MotorControllerGroup(sparkLtBack, sparkLtFront, sparkLtMiddle);
    rightMotors = new MotorControllerGroup(sparkRtBack, sparkRtFront, sparkRtMiddle);

    rigelDriveTrain = new DifferentialDrive(leftMotors, rightMotors);
    rigelDriveTrain.setDeadband(DEADBAND);
  }

  public void SetCoastMode() {
    sparkLtFront.setIdleMode(IdleMode.kCoast);
    sparkLtMiddle.setIdleMode(IdleMode.kCoast);
    sparkLtBack.setIdleMode(IdleMode.kCoast);

    sparkRtFront.setIdleMode(IdleMode.kCoast);
    sparkRtMiddle.setIdleMode(IdleMode.kCoast);
    sparkRtBack.setIdleMode(IdleMode.kCoast);
  }

  public void SetBrakeMode() {
    sparkLtFront.setIdleMode(IdleMode.kBrake);
    sparkLtMiddle.setIdleMode(IdleMode.kBrake);
    sparkLtBack.setIdleMode(IdleMode.kBrake);
    
    sparkRtFront.setIdleMode(IdleMode.kBrake);
    sparkRtMiddle.setIdleMode(IdleMode.kBrake);
    sparkRtBack.setIdleMode(IdleMode.kBrake);
  }

  public static RigelDriveTrain getInstance()
  {
    if(instance == null)
    {
      instance = new RigelDriveTrain();
    } 
    return instance;
  }
}
