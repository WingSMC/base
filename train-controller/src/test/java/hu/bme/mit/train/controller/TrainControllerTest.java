package hu.bme.mit.train.controller;

import org.junit.Assert;
import org.junit.Test;
import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerTest {

	@Test
	public void SpeedLimit_Test() {
		TrainController controller = new TrainControllerImpl();

		controller.setSpeedLimit(50);

		controller.setJoystickPosition(50);
		controller.followSpeed();

		Assert.assertEquals(50, controller.getReferenceSpeed());

		controller.setSpeedLimit(10);
		controller.setJoystickPosition(5);
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());

	}
}
