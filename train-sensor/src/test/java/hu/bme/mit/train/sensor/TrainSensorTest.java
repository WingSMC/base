package hu.bme.mit.train.sensor;

import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.*;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    private TrainSensor sensor;
    private TrainController controller;
    private TrainUser user;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void testOverrideSpeedLimitRefspeed() throws Exception {
        when(controller.getReferenceSpeed()).thenReturn(10);
        sensor.overrideSpeedLimit(4);

        verify(user).setAlarmState(true);

    }

    @Test
    public void testOverrideSpeedLimitLt0() throws Exception {
        when(controller.getReferenceSpeed()).thenReturn(0);
        sensor.overrideSpeedLimit(-1);

        verify(user).setAlarmState(true);

    }

    @Test
    public void testOverrideSpeedLimitGt500() throws Exception {
        when(controller.getReferenceSpeed()).thenReturn(500);
        sensor.overrideSpeedLimit(501);

        verify(user).setAlarmState(true);

    }

    @Test
    public void testOverrideSpeedLimit() throws Exception {
        when(controller.getReferenceSpeed()).thenReturn(10);
        sensor.overrideSpeedLimit(8);

        verify(user).setAlarmState(false);

    }
}
