package cn.droidlover.xdroid.kit;

import android.os.Environment;

import java.io.File;

/**
 * 文件操作工具类
 * Created by 美时美课 on 2017/4/12.
 */

public class FileUtils {
    /**
     * 创建初始文件夹。保存拍摄图片和裁剪后的图片
     *
     * @param filePath 文件夹路径
     */
    public static void createFile(String filePath) {
        String externalStorageState = Environment.getExternalStorageState();

        File dir = new File(Environment.getExternalStorageDirectory() + filePath);

        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }


    /**
     * @param filePath 文件夹路径
     * @return 截图完成的 file
     */
    public static String getFilePath(String filePath) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + filePath;
    }
}
