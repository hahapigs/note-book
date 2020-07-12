/*
 * notebook
 *
 * Copyright (c) 2019~2020，zhaohongliang  Email：15711385916@163.com
 *
 * https://github.com/Chunghwa-2018/note-book.git
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.notebook.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * BusinessErrorCodeEnum
 *
 * <p>业务异常枚举类型</p>
 *
 * @author: zhaohongliang
 * @date: 2020-07-12 17:40
 * @version: 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor
public enum BusinessErrorCodeEnum implements BaseError {

    /**
     * 商户不存在
     */
    CUSTOMER_NOT_FOUND(1001, "商户不存在");

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String msg;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public BaseError setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
