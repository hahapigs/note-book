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

package com.example.notebook.result;


/**
 * SuccessResponseResult
 *
 * <p>返回成功结果集封装</p>
 *
 * @author: zhaohongliang
 * @date: 2020-07-12 20:07
 * @version: 1.0.0
 */
public class SuccessResponseResult extends ResponseResult {

    private static final Integer DEFAULT_SUCCESS_CODE = 200;

    private static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";

    public SuccessResponseResult() {
        super(DEFAULT_SUCCESS_CODE, true, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public SuccessResponseResult(Object data) {
        super(DEFAULT_SUCCESS_CODE, true, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public SuccessResponseResult(Integer code, String message) {
        super(code, true, message, null);
    }

    public SuccessResponseResult(Integer code, String message, Object data) {
        super(code, true, message, data);
    }
}
