package cc.southseast.controller.function.reset;

import cc.southseast.controller.verification.reset.MailUtil;
import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.hint.HintPanel;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.nutz.dao.Cnd;

import java.util.Random;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.CODE;
import static cc.southseast.controller.base.ToGetData.USER;
import static cc.southseast.view.ui.base.StaticData.EMPNO_INSERT_ERROR_HINT;
import static cc.southseast.view.ui.base.StaticData.SEND_EMAIL_HINT;
import static com.xiaoleilu.hutool.util.NumberUtil.isInteger;

/**
 * @Author: Southseast
 * @Date: 2019-01-09 23:52
 * @Version 1.0
 */
public class ToSendEmail implements EventHandler<ActionEvent> {

    private JFXTextField idInput;

    public ToSendEmail(JFXTextField idInput) {
        this.idInput = idInput;
    }

    @Override
    public void handle(ActionEvent event) {

        // 获取账号
        String id = idInput.getText().trim();

        // 判断输入是否为空以及账号长度
        if (id.length() < 16 && isInteger(id)) {

            // 判断账号是否存在
            if (dao.fetch(Employee.class, Long.parseLong(id)) != null) {

                // 获取用户信息
                USER = dao.fetchByJoin(Employee.class, null, Cnd.where("empNo", "=", id));

                Random random = new Random();

                for (int i = 0; i < 8; i++) {
                    CODE += random.nextInt(10);
                }

                MailUtil mailUtil = new MailUtil(USER.getEmpEmail(), CODE);

                mailUtil.run();

                // 提示
                new HintPanel(idInput, SEND_EMAIL_HINT);
            } else {

                // 提示
                new HintPanel(idInput, EMPNO_INSERT_ERROR_HINT);
            }
        } else {

            // 提示
            new HintPanel(idInput, EMPNO_INSERT_ERROR_HINT);
        }
    }
}
