package com.qiaoxg.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class ClassUtil {

    public static Object getClassByInterface(Class c) throws Exception {
        List<Class> classList = getAllClassByInterface(c);

        Object obj = null;
        if (classList != null && classList.size() > 0) {
            Class<?> cls = Class.forName(classList.get(0).getName());
            obj =  cls.newInstance();
        }

        return obj;
    }

    /**
     * @param c �ӿ�
     * @return List<Class>    ʵ�ֽӿڵ�������
     * @Description: ����һ���ӿڷ��ظýӿڵ�������
     */
    @SuppressWarnings("unchecked")
    public static List<Class> getAllClassByInterface(Class c) {
        List returnClassList = new ArrayList<Class>();
        //�ж��ǲ��ǽӿ�,���ǽӿڲ�������
//        if (c.isInterface()) {
        String packageName = c.getPackage().getName();  //��õ�ǰ����
        try {
            List<Class> allClass = getClasses1(packageName);//��õ�ǰ���Լ��Ӱ��µ�������(java�п��ã���android������)
//            List<Class> allClass = getClasses(packageName, c);//��õ�ǰ���Լ��Ӱ��µ�������(android����)
 
            //�ж��Ƿ���һ���ӿ�
            for (int i = 0; i < allClass.size(); i++) {
                if (c.isAssignableFrom(allClass.get(i))) {
                    if (!c.equals(allClass.get(i))) {
                        returnClassList.add(allClass.get(i));
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
//        }
        return returnClassList;
    }
 
//    /**
//     * @return List<Class>    ����������
//     * @Description: ���ݰ�����øð��Լ��Ӱ��µ������಻����jar���е�
//     *               ��õ�ǰ���Լ��Ӱ��µ�������(android����)
//     */
//    private static List<Class> getClasses(String packageName, Class<?> clazz) throws ClassNotFoundException,
//            IOException {
// 
//        ArrayList<Class> classes = new ArrayList<Class>();
//        List<DexFile> dexFiles = new ArrayList<>();
//        try {
//            BaseDexClassLoader classLoader = ((BaseDexClassLoader) Thread.currentThread().getContextClassLoader());
//            Field pathListField = classLoader.getClass().getSuperclass().getDeclaredField("pathList");
//            pathListField.setAccessible(true);
//            Object pathList = pathListField.get(classLoader);
// 
//            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
//            dexElementsField.setAccessible(true);
//            Object dexElements = dexElementsField.get(pathList);
//            int dexLength = Array.getLength(dexElements);
//            Field dexFileField = null;
// 
//            for (int i = 0; i < dexLength; i++) {
//                Object dexElement = Array.get(dexElements, i);
//                if (dexFileField == null) {
//                    dexFileField = dexElement.getClass().getDeclaredField("dexFile");
//                    dexFileField.setAccessible(true);
//                }
//                DexFile dexFile = (DexFile) dexFileField.get(dexElement);
//                if (dexFile != null) {
//                    dexFiles.add(dexFile);
//                }
//            }
// 
//            for (DexFile file : dexFiles) {
//                for (Enumeration<String> entries = file.entries(); entries.hasMoreElements(); ) {
//                    final String s1 = entries.nextElement();
//                    if (s1.contains(IApplication.mInstance.getPackageName())) {
//                        if (clazz.isAssignableFrom(Class.forName(s1))) {
//                            classes.add(Class.forName(s1));
//                        }
//                    }
//                }
//            }
//            Toast.makeText(IApplication.mInstance, Integer.toString(classes.size()), Toast.LENGTH_LONG).show();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return classes;
//    }
 
 
    /**
     * @return List<Class>    ����������
     * @Description: ���ݰ�����øð��Լ��Ӱ��µ������಻����jar���е�
     */
    private static List<Class> getClasses1(String packageName) throws ClassNotFoundException,
            IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String newPath = resource.getFile().replace("%20", " ");
            dirs.add(new File(newPath));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClass(directory, packageName));
        }
        return classes;
    }
 
    private static List<Class> findClass(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClass(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() -
                        6)));
            }
        }
        return classes;
    }
 
//    @SuppressWarnings("unchecked")
//    public static List<Class> getAllClassByAnnotation(Class annotationClass) {
//        List returnClassList = new ArrayList<Class>();
//        //�ж��ǲ���ע��
//        if (annotationClass.isAnnotation()) {
//            String packageName = annotationClass.getPackage().getName();    //��õ�ǰ����
//            try {
//                List<Class> allClass = getClasses(packageName, CustomDrawPointBasic.class);//��õ�ǰ���Լ��Ӱ��µ�������
// 
//                for (int i = 0; i < allClass.size(); i++) {
//                    if (allClass.get(i).isAnnotationPresent(annotationClass)) {
//                        returnClassList.add(allClass.get(i));
//                    }
//                }
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
//        return returnClassList;
//    }


}
