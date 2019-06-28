package cc.southseast.controller.base;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 8:45 AM
 * @Version 1.0
 * 窗口拖动
 */
public class ToDrag implements EventHandler<MouseEvent> {


    private double xOffset = 0;
    private double yOffset = 0;
    private Stage stage;

    public ToDrag(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(MouseEvent event) {
        event.consume();
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            stage.setX(event.getScreenX() - xOffset);
            if (event.getScreenY() - yOffset < 0) {
                stage.setY(0);
            } else {
                stage.setY(event.getScreenY() - yOffset);
            }
        }
    }

    public void enableDrag(Node node) {
        node.setOnMousePressed(this);
        node.setOnMouseDragged(this);
    }
}
