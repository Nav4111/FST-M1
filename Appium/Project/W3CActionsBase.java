package examples;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

// The Base class that contains the actions
public class W3CActionsBase {
    // Create pointer
    private static final PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    // Create a scroll action
    public static void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
        // Create sequence of actions
        Sequence swipe = new Sequence(pointer, 1)

                .addAction(pointer.createPointerMove(Duration.ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(pointer.createPointerDown(LEFT.asArg()))
                .addAction(pointer.createPointerMove(Duration.ofMillis(duration), viewport(), end.getX(), end.getY()))
                .addAction(pointer.createPointerUp(LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

}
