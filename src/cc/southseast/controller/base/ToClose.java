package cc.southseast.controller.base;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 8:45 AM
 * @Version 1.0
 * 窗口关闭
 */
public class ToClose implements EventHandler<ActionEvent> {

    private Stage primaryStage;

    public ToClose(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent event) {
        // 关闭功能
        primaryStage.close();
    }
}
