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
package net.hasor.core.event;
import net.hasor.core.AsyncCallBackHook;
/**
 * 异步事件回调接口。
 * @version : 2013-4-12
 * @author 赵永春 (zyc@hasor.net)
 */
class EmptyAsyncCallBackHook implements AsyncCallBackHook {
    public void handleException(String eventType, Object[] objects, Throwable e) {}
    public void handleComplete(String eventType, Object[] objects) {}
}