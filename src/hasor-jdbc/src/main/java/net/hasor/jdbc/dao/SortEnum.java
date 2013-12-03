/*
 * Copyright 2002-2006 the original author or authors.
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
package net.hasor.jdbc.dao;
/**
 * 
 * @version : 2013-11-26
 * @author ������(zyc@hasor.net)
 */
public enum SortEnum {
    Asc("Asc"), Desc("Desc"), ;
    private String val;
    public String getVal() {
        return val;
    }
    private SortEnum(String val) {
        this.val = val;
    }
}