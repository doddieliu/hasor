package org.hasor.test.mvc.plugin.safety.support;
import java.lang.reflect.Method;
import net.hasor.core.ApiBinder;
import net.hasor.core.ModuleSettings;
import net.hasor.core.anno.DefineModule;
import net.hasor.core.module.AbstractHasorModule;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hasor.test.mvc.plugin.safety.Power;
import org.hasor.test.mvc.plugin.safety.SafetyContext;
import com.google.inject.matcher.AbstractMatcher;
/**
 * Ȩ�޷���
 * @version : 2013-4-8
 * @author ������ (zyc@hasor.net)
 */
@DefineModule(displayName = "SafetyPlatformListener", description = "org.test.plugin.safety����������֧�֡�")
public class SafetyPlatformListener extends AbstractHasorModule {
    private SafetyContext safetyContext = null;
    public void configuration(ModuleSettings info) {}
    public void init(ApiBinder apiBinder) {
        //1.����Aop
        apiBinder.getGuiceBinder().bindInterceptor(new ClassPowerMatcher(), new MethodPowerMatcher(), new PowerInterceptor());
        //
        apiBinder.getGuiceBinder().bind(SafetyContext.class).asEagerSingleton();
    }
    /*-------------------------------------------------------------------------------------*/
    /*���������Ƿ�ƥ�䡣����ֻҪ���ͻ򷽷��ϱ����@Power��*/
    private class ClassPowerMatcher extends AbstractMatcher<Class<?>> {
        public boolean matches(Class<?> matcherType) {
            if (matcherType.isAnnotationPresent(Power.class) == true)
                return true;
            Method[] m1s = matcherType.getMethods();
            Method[] m2s = matcherType.getDeclaredMethods();
            for (Method m1 : m1s) {
                if (m1.isAnnotationPresent(Power.class) == true)
                    return true;
            }
            for (Method m2 : m2s) {
                if (m2.isAnnotationPresent(Power.class) == true)
                    return true;
            }
            return false;
        }
    }
    /*�����ⷽ���Ƿ�ƥ�䡣���򣺷����򷽷��������ϱ����@Power��*/
    private class MethodPowerMatcher extends AbstractMatcher<Method> {
        public boolean matches(Method matcherType) {
            if (matcherType.isAnnotationPresent(Power.class) == true)
                return true;
            if (matcherType.getDeclaringClass().isAnnotationPresent(Power.class) == true)
                return true;
            return false;
        }
    }
    /*������*/
    private class PowerInterceptor implements MethodInterceptor {
        public Object invoke(MethodInvocation invocation) throws Throwable {
            //1.��ȡȨ������
            Method targetMethod = invocation.getMethod();
            Power cacheAnno = targetMethod.getAnnotation(Power.class);
            if (cacheAnno == null)
                cacheAnno = targetMethod.getDeclaringClass().getAnnotation(Power.class);
            if (cacheAnno == null)
                return invocation.proceed();
            //2.��ȡKey
            if (safetyContext.checkPower(cacheAnno.value()) == false)
                throw new Exception("no Power");
            return invocation.proceed();
        }
    }
}