package com.baidu.disconf.web.web.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.disconf.web.service.sign.form.SigninForm;
import com.baidu.disconf.web.service.sign.service.SignMgr;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.service.UserMgr;
import com.baidu.dsp.common.exception.FieldException;

/**
 * 权限验证
 *
 * @author liaoqiqi
 * @version 2014-7-2
 */
@Component
public class AuthValidator {

    @Autowired
    private SignMgr signMgr;

    @Autowired
    private UserMgr userMgr;

    /**
     * 验证登录
     */
    public void validateLogin(SigninForm signinForm) {

        //
        // 校验用户是否存在
        //
        User user = signMgr.getUserByName(signinForm.getName());
        if (user == null) {
            throw new FieldException(SigninForm.Name, "user.not.exist", null);
        }

        // 校验密码
        if (!signMgr.validate(user.getPassword(), signinForm.getPassword())) {
            throw new FieldException(SigninForm.PASSWORD, "password.not.right", null);
        }
    }
}
