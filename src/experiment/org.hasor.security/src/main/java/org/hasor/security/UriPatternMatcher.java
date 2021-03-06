/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
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
package org.hasor.security;
/**
 * 对URI进行权限判断接口。
 * @version : 2013-4-9
 * @author 赵永春 (zyc@byshell.org)
 */
public abstract class UriPatternMatcher {
    private String requestURI = null;
    //
    protected UriPatternMatcher(String requestURI) {
        this.requestURI = requestURI;
    }
    /**获取requestURI*/
    public String getRequestURI() {
        return requestURI;
    }
    /**在权限会话中检测是否具有权限。*/
    public abstract boolean testPermission(AuthSession authSession);
    /**在权限会话中检测是否具有权限。*/
    public boolean testPermission(AuthSession[] authSessions) {
        if (authSessions == null)
            return false;
        for (AuthSession authSession : authSessions)
            if (this.testPermission(authSession) == true)
                return true;
        return false;
    }
    public String toString() {
        return this.getClass().getSimpleName() + " at requestURI= " + requestURI;
    }
}