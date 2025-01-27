package com.argyle.argylepicturebackend.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * @Author: hjm
 * @Date: 2025/01/23/22:45
 * @Description:
 */
@Getter
public enum UserRoleEnum {
    ADMIN("管理员", "admin"),
    USER("普通用户", "user");
    private final String text;
    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }
    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
