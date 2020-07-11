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

package com.example.notebook.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * bookProperties
 *
 * <p>book配置文件</p>
 *
 * @author: zhaohongliang
 * @date: 2020-07-12 04:53
 * @version: 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "book", ignoreInvalidFields = false)
@PropertySource(value = {"classpath:book.properties"},
        ignoreResourceNotFound = false, encoding = "UTF-8", name = "book.properties")
public class BookProperties {

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 住址
     */
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
