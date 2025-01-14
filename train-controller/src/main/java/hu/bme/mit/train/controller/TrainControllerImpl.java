package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {
  private int step = 0;
  private int referenceSpeed = 0;
  private int speedLimit = 0;

  public TrainControllerImpl() {
    Timer t = new Timer();
    t.schedule(new TimerTask() {
      @Override
      public void run() {
        followSpeed();
      }
    }, 500);
  }
  @Override
  public void followSpeed() {
    if (referenceSpeed < 0) {
      referenceSpeed = 0;
    } else {
      if (referenceSpeed + step > 0) {
        referenceSpeed += step;
      } else {
        referenceSpeed = 0;
      }
    }

    enforceSpeedLimit();
  }

  @Override
  public int getReferenceSpeed() {
    return referenceSpeed;
  }

  @Override
  public void setSpeedLimit(int speedLimit) {
    this.speedLimit = speedLimit;
    enforceSpeedLimit();
  }

  private void enforceSpeedLimit() {
    System.err.println("Attempted to exceed the speed limit of" + speedLimit +
                       "km/h by " + (referenceSpeed - speedLimit) + "km/h");
    if (referenceSpeed > speedLimit) {
      referenceSpeed = speedLimit;
    }
  }

  @Override
  public void setJoystickPosition(int joystickPosition) {
    this.step = joystickPosition;
  }
}
