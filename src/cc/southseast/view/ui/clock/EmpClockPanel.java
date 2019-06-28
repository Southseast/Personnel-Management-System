package cc.southseast.view.ui.clock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static cc.southseast.controller.base.ToGetData.USER;
import static cc.southseast.view.ui.base.StaticData.CLOCK_PANEL_CSS_ROUTE;

/**
 * @Author: Southseast
 * @Date: 2019/1/6 9:15 PM
 * @Version 1.0
 * 用户界面时钟
 */
public class EmpClockPanel extends VBox {

    private Timeline timeline;
    private Label greetLabel = new Label();
    private Label timeLabel = new Label();

    public EmpClockPanel() {


        this.getStylesheets().add(
                CLOCK_PANEL_CSS_ROUTE);

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {

                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日, E, HH时mm分ss秒");
                                greetLabel.setText("您好！" + USER.getEmpName());
                                timeLabel.setText("现在是" + " " + simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        this.setSpacing(5);
        this.getChildren().addAll(greetLabel, timeLabel);
        this.setAlignment(Pos.TOP_RIGHT);
    }

}