package ru.luli.decompiler.utils;

import android.os.Environment;
import ru.luli.compiler.api.TestMaquette;
import ru.luli.equipment.SessionEquipment;

import java.io.*;

public class BasicUtils {
    private static final File BASE_EXT_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    private static final File BASE_DIR = Environment.getDataDirectory();
    private static final String WORK_DIR_NAME = "DecompileTest";
    private static final String LOGIN_INFO_DIR_NAME = "loginInfo";

    public static boolean isBlank(String value) {
        return value == null || value.length() < 1;
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static String getLoginInfoPath() {
        return BASE_DIR.getAbsolutePath() + '/' + WORK_DIR_NAME + '/' + LOGIN_INFO_DIR_NAME;
    }

    public static File getLoginInfoDir() {
        return new File(getLoginInfoPath());
    }

    public static void saveLoginInfo(SessionEquipment equip) {
        try {
            saveToObject(equip);
        } catch (Exception e) {
            LogUtils.processError(e.getMessage(), e, BasicUtils.class);
        }
    }

    public static SessionEquipment getLoginInfo() {
        try {
            File dir = getLoginInfoDir();
            if (dir.listFiles().length < 1) {
                throw new Exception("Nobody is active!");
            }
            File candidat = null;
            long lastModify = 0;
            for (File f : dir.listFiles()) {
                if (f.lastModified() > lastModify) {
                    lastModify = f.lastModified();
                    candidat = f;
                }
            }
            if (candidat==null) {
                throw new Exception("nobody is Active!");
            }
            Object result = getObjectFromFile(candidat.getAbsolutePath());
            return (SessionEquipment) result;
        } catch (IOException e) {
            LogUtils.processError(e.getMessage(), e, BasicUtils.class);
        } catch (Exception e) {
            LogUtils.processError(e.getMessage(), e, BasicUtils.class);
        }
        return null;
    }

    public static TestMaquette getTestMaquette(String maquettPath) {
        try {
            Object object = getObjectFromFile(maquettPath);
            if (object != null) {
                return (TestMaquette) object;
            }
        } catch (IOException e) {
            LogUtils.processError(e.getMessage(), e, BasicUtils.class);
        }
        return null;
    }

    private static Object getObjectFromFile(String pathToFile) throws IOException {
        ObjectInputStream ois = null;
        InputStream is = null;
        try {
            File candidat = new File(pathToFile);
            is = new FileInputStream(candidat);
            ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (Exception e) {
            LogUtils.processError(e.getMessage(), e, BasicUtils.class);
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    private static void saveToObject(Object object) throws IOException {
        OutputStream os = null;
        ObjectOutputStream ous = null;
        try {
            String path = getLoginInfoPath();
            File dir = new File(path);
            if (dir.listFiles() != null && dir.listFiles().length > 0) {
                for (File con : dir.listFiles()) {
                    con.delete();
                }
            }
            os = new FileOutputStream(new File(path + '/' + System.currentTimeMillis()));
            ous = new ObjectOutputStream(os);
            ous.writeObject(object);
            ous.flush();
        } catch (Exception e) {
            LogUtils.processError(e.getMessage(), e, BasicUtils.class);
        } finally {
            if (ous != null) {
                ous.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }


}
