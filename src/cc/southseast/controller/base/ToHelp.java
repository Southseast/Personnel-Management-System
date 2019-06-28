package cc.southseast.controller.base;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Southseast
 * @Date: 2019-01-11 09:34
 * @Version 1.0
 */
public class ToHelp implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        try {
            Desktop.getDesktop().open(new File("README.md"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
