package dev.muse.irlight;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Mi Remote 7.1.3G: enable the bundled ceiling-light IR category.
 * Category 14 = DKDeviceType.DK_DEVICE_TYPE_LIGHT (verified in uploaded APK).
 */
public final class IrLightHook implements IXposedHookLoadPackage {
    private static final String TAG = "[IRLightLSPosed] ";
    private static final String PACKAGE = "com.duokan.phone.remotecontroller";
    private static final String TYPE_CLASS =
            "com.xiaomi.mitv.phone.remotecontroller.ir.dk.model.DKDeviceType";
    private static final int LIGHT_ID = 14;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) {
        if (!PACKAGE.equals(param.packageName)) return;
        final ClassLoader loader = param.classLoader;
        final Class<?> typeClass = XposedHelpers.findClassIfExists(TYPE_CLASS, loader);
        if (typeClass == null) {
            XposedBridge.log(TAG + "DKDeviceType missing. Unsupported app version.");
            return;
        }
        try {
            XposedHelpers.findAndHookMethod(typeClass, "setSupported", boolean.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam hook) {
                            if (isLight(hook.thisObject)) {
                                XposedHelpers.setBooleanField(hook.thisObject,
                                        "mSupported", true);
                            }
                        }
                    });
            XposedHelpers.findAndHookMethod(typeClass, "isSupported",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam hook) {
                            if (isLight(hook.thisObject)) hook.setResult(true);
                        }
                    });
            XposedHelpers.findAndHookMethod(typeClass, "getDisplayName",
                    android.content.Context.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam hook) {
                            if (!isLight(hook.thisObject)) return;
                            Object original = hook.getResult();
                            if (original == null || original.toString().trim().isEmpty()) {
                                hook.setResult("ja".equals(Locale.getDefault().getLanguage())
                                        ? "照明" : "Lighting");
                            }
                        }
                    });
            hookCategoryList(loader, typeClass,
                    "com.xiaomi.mitv.phone.remotecontroller.ir.activity.AddDeviceActivityV52",
                    "I");
            hookCategoryList(loader, typeClass,
                    "com.xiaomi.mitv.phone.remotecontroller.ir.activity.MiUIAddDeviceActivityV52",
                    "L");
            XposedBridge.log(TAG + "Mi Remote lighting category hooks installed (ID=14)");
        } catch (Throwable e) {
            XposedBridge.log(TAG + "hook setup failed: " + e);
        }
    }

    private static boolean isLight(Object model) {
        if (model == null) return false;
        try {
            return XposedHelpers.getIntField(model, "deviceid") == LIGHT_ID;
        } catch (Throwable ignored) {
            return false;
        }
    }

    private static void hookCategoryList(ClassLoader loader, Class<?> typeClass,
                                         String activityName, String method) {
        Class<?> activity = XposedHelpers.findClassIfExists(activityName, loader);
        if (activity == null) {
            XposedBridge.log(TAG + "activity not found: " + activityName);
            return;
        }
        try {
            XposedHelpers.findAndHookMethod(activity, method, List.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam hook) {
                            Object original = hook.getResult();
                            if (!(original instanceof List)) return;
                            List<?> existing = (List<?>) original;
                            for (Object item : existing) {
                                if (isLight(item)) return; // Already present from server.
                            }
                            // Avoid presenting an artificial category during a total API failure.
                            if (existing.isEmpty()) return;
                            try {
                                Object light = XposedHelpers.newInstance(typeClass);
                                XposedHelpers.setIntField(light, "deviceid", LIGHT_ID);
                                Properties names = new Properties();
                                names.setProperty("cn", "灯泡");
                                names.setProperty("tw", "燈泡");
                                names.setProperty("en", "Lighting");
                                names.setProperty("ja", "照明");
                                XposedHelpers.callMethod(light, "setNames", names);
                                XposedHelpers.callMethod(light, "setSupported", true);
                                List<Object> expanded = new ArrayList<>(existing.size() + 1);
                                expanded.addAll(existing);
                                expanded.add(light);
                                hook.setResult(expanded);
                                XposedBridge.log(TAG + "added lighting category ID=14 after filtering in "
                                        + activityName + "." + method);
                            } catch (Throwable e) {
                                XposedBridge.log(TAG + "category insertion skipped: " + e);
                            }
                        }
                    });
        } catch (Throwable e) {
            XposedBridge.log(TAG + "activity hook skipped: " + activityName + ": " + e);
        }
    }
}
