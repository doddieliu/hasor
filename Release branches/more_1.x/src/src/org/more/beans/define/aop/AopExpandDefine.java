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
package org.more.beans.define.aop;
/**
 * ����{@link AopExpandDefine}����
 * @version 2010-9-25
 * @author ������ (zyc@byshell.org)
 */
public class AopExpandDefine implements org.more.beans.define.ExpandDefine {
    private Object    target    = null; //��չĿ��
    private AopConfigDefine aopConfig = null; //��չ��aop����
    /**��ȡ��չ��aop���ԡ�*/
    public AopConfigDefine getAopConfig() {
        return aopConfig;
    }
    /**������չ��aop���ԡ�*/
    public void setAopConfig(AopConfigDefine aopConfig) {
        this.aopConfig = aopConfig;
    }
    /**��ȡ��չĿ�ꡣ*/
    public Object getTarget() {
        return this.target;
    }
    /**������չĿ�ꡣ*/
    public void setTarget(Object target) {
        this.target = target;
    }
}