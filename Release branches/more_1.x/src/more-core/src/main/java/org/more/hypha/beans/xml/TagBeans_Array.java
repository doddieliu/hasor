/*
 * Copyright 2008-2009 the original author or authors.
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
package org.more.hypha.beans.xml;
import org.more.core.xml.XmlStackDecorator;
import org.more.hypha.context.xml.XmlDefineResource;
import org.more.hypha.define.Array_ValueMetaData;
/**
 * ���ڽ���array��ǩ
 * @version 2010-9-23
 * @author ������ (zyc@byshell.org)
 */
public class TagBeans_Array extends TagBeans_AbstractCollection<Array_ValueMetaData> {
    /**����{@link TagBeans_Array}����*/
    public TagBeans_Array(XmlDefineResource configuration) {
        super(configuration);
    }
    /**����{@link Array_ValueMetaData}����*/
    protected Array_ValueMetaData createDefine(XmlStackDecorator<Object> context) {
        return new Array_ValueMetaData();
    }
    /**����Ĭ�ϼ�����������*/
    protected Class<?> getDefaultCollectionType() {
        return Object.class;
    }
}