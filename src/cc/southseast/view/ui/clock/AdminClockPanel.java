package cc.southseast.view.ui.clock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static cc.southseast.view.ui.base.StaticData.BACKGROUND_IMAGE_CSS_ID;
import static cc.southseast.view.ui.base.StaticData.CLOCK_PANEL_CSS_ROUTE;

/**
 * @Author: Southseast
 * @Date: 2019/1/6 9:15 PM
 * @Version 1.0
 * 管理界面时钟
 */
public class AdminClockPanel extends VBox {

    private Timeline timeline;
    private Label greet1 = new Label();
    private Label greet2 = new Label();
    private Label time1 = new Label();
    private Label time2 = new Label();
    private Label time3 = new Label();

    private HBox backgroundImage = new HBox();

    public AdminClockPanel() {

        this.getStylesheets().add(
                CLOCK_PANEL_CSS_ROUTE);

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {

                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat week = new SimpleDateFormat("E");
                                SimpleDateFormat hourMinuteSecond = new SimpleDateFormat("HH:mm:ss");
                                greet1.setText("您好！");
                                greet2.setText("现在是");
                                time1.setText(yearMonthDay.format(time.getTime()));
                                time2.setText(week.format(time.getTime()));
                                time3.setText(hourMinuteSecond.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        backgroundImage.setId(BACKGROUND_IMAGE_CSS_ID);
        backgroundImage.setMinSize(100, 106);
        backgroundImage.setMaxSize(100, 106);
        this.setSpacing(5);
        this.getChildren().addAll(greet1, backgroundImage, greet2, time1, time2, time3);
        this.setAlignment(Pos.CENTER);
    }

}