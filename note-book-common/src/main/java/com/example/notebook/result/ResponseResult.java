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

import lombok.*;

/**
 * ErrorMessage
 *
 * <p>返回结果集</p>
 *
 * @author: zhaohongliang
 * @date: 2020-07-12 17:32
 * @version: 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    /**
     * 返回状态码
     */
    @Setter
    @Getter
    private Integer code;

    /**
     * 返回状态
     */
    @Setter
    @Getter
    private Boolean success;

    /**
     * 返回信息
     */
    @Setter
    @Getter
    private String message;

    /**
     * 返回数据
     */
    @Setter
    @Getter
    private T data;

}
