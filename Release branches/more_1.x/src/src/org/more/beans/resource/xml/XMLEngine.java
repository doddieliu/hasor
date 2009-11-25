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
package org.more.beans.resource.xml;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.more.InitializationException;
import org.more.core.io.AutoCloseInputStream;
import org.more.util.attribute.AttBase;
/**
 * XML�������棬������Ա����ͨ��ʵ��DoEventIteration�ӿ�ʹ��runTask������ִ����չ��xml����
 * <br/>Date : 2009-11-23
 * @author ������
 */
public class XMLEngine extends AttBase {
    /**  */
    private static final long             serialVersionUID = 2880738501389974190L;
    //========================================================================================Field
    protected HashMap<String, TagProcess> tagProcessMap    = new HashMap<String, TagProcess>(); //��ǩ��������
    protected HashMap<String, Class<?>>   taskProcessMap   = new HashMap<String, Class<?>>();  //����������
    //==================================================================================Constructor
    /**����XMLEngine����*/
    public XMLEngine() {
        try {
            Class<?> type = XMLEngine.class;
            InputStream in = type.getResourceAsStream("/org/more/beans/resource/xml/tagProcess.properties");
            Properties pro = new Properties();
            pro.load(new AutoCloseInputStream(in));//װ�������ļ�
            for (Object key : pro.keySet()) {
                String kn = key.toString();
                String classType = pro.getProperty(kn);
                tagProcessMap.put(kn, (TagProcess) Class.forName(classType).newInstance());
            }
            /*---------------*/
            in = type.getResourceAsStream("/org/more/beans/resource/xml/taskProcess.properties");
            pro = new Properties();
            pro.load(new AutoCloseInputStream(in));//װ�������ļ�
            for (Object key : pro.keySet()) {
                String kn = key.toString();
                String classType = pro.getProperty(kn);
                taskProcessMap.put(kn, Class.forName(classType));
            }
        } catch (Exception e) {
            throw new InitializationException("�޷���ʼ��XMLEngine������װ�ػ�����Դ�ļ�ʱ�����쳣��msg=" + e.getMessage());
        }
    }
    //========================================================================================Event
    /**ɨ��XML��processXPath��Ҫ������xpathƥ���������ʽ��*/
    protected Object scanningXML(InputStream in, String processXPath, TaskProcess doEventIteration) throws XMLStreamException {
        XMLStreamReader reader = this.getXMLStreamReader(in);
        ContextStack stack = null;//��ǰ��ջ
        String onTag = null;//��ǰ��ǩ
        int event = reader.getEventType();//��ǰ�¼�����
        while (true) {
            switch (event) {
            case XMLStreamConstants.START_DOCUMENT://�ĵ���ʼ
                stack = new ContextStack(null, null, "/");
                doEventIteration.onEvent(stack, stack.getXPath(), event, reader, tagProcessMap);
                break;
            case XMLStreamConstants.END_DOCUMENT://�ĵ�����
                doEventIteration.onEvent(stack, stack.getXPath(), event, reader, tagProcessMap);
                break;
            case XMLStreamConstants.START_ELEMENT://Ԫ�ؿ�ʼ
                onTag = reader.getLocalName();
                stack = new ContextStack(stack, onTag, stack.getXPath() + onTag + "/");
                doEventIteration.onEvent(stack, stack.getXPath(), event, reader, tagProcessMap);
                int attCount = reader.getAttributeCount();
                for (int i = 0; i < attCount; i++) {//��������
                    String key = reader.getAttributeLocalName(i);
                    stack.attValue = reader.getAttributeValue(i);
                    doEventIteration.onEvent(stack, stack.getXPath() + "@" + key, XMLStreamConstants.ATTRIBUTE, reader, tagProcessMap);
                    stack.attValue = null;
                }
                break;
            case XMLStreamConstants.END_ELEMENT://Ԫ�ؽ���
                doEventIteration.onEvent(stack, stack.getXPath(), event, reader, tagProcessMap);
                stack = stack.getParent();
                break;
            case XMLStreamConstants.CDATA://������CDATA
                doEventIteration.onEvent(stack, stack.getXPath(), event, reader, tagProcessMap);
                break;
            case XMLStreamConstants.CHARACTERS://�������ַ�
                doEventIteration.onEvent(stack, stack.getXPath(), event, reader, tagProcessMap);
                break;
            }
            if (reader.hasNext() == false)
                break;
            event = reader.next();
        }
        return doEventIteration.getResult();
    }
    /**��ȡStax�Ķ��������Ķ�����������COMMENT�ڵ㡣*/
    private XMLStreamReader getXMLStreamReader(InputStream in) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(in);
        reader = factory.createFilteredReader(reader, new StreamFilter() {
            @Override
            public boolean accept(XMLStreamReader reader) {
                int event = reader.getEventType();
                if (event == XMLStreamConstants.COMMENT)
                    return false;
                else
                    return true;
            }
        });
        return reader;
    }
    //==========================================================================================Job
    /**ִ��XML����*/
    public Object runTask(InputStream xmlStream, TaskProcess task, String processXPath, Object... params) throws Exception {
        task.setConfig(params);
        return this.scanningXML(xmlStream, processXPath, task);
    }
    /**ִ��XML����*/
    public Object runTask(InputStream xmlStream, String taskName, String processXPath, Object... params) throws Exception {
        TaskProcess task = (TaskProcess) this.taskProcessMap.get(taskName).newInstance();
        task.setConfig(params);
        return this.scanningXML(xmlStream, processXPath, task);
    }
}