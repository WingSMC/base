package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;

import static org.mockito.Mockito.*;

public class TrainControllerTest {

	@Test
	public void SpeedLimit_Test() {
		TrainSystem system = new TrainSystem();
		TrainController controller = system.getController();
		TrainSensor sensor = system.getSensor();
		TrainUser user = system.getUser();

		controller.setSpeedLimit(50);

		Assert.assertEquals(0, controller.getReferenceSpeed());

		controller.setJoystickPosition(50);
		controller.followSpeed();

		Assert.assertEquals(50, controller.getReferenceSpeed());

		controller.setSpeedLimit(10);
		controller.setJoystickPosition(5);
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}
}
