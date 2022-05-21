package com.zxk.study.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 *
 * 文件操作工具类
 * 实现文件的创建、删除、复制、压缩、解压以及目录的创建、删除、复制、压缩解压等功能
 * @author zhouxx
 * @create 2022-04-14 22:17
 */
@Slf4j
public class FileUtils {

    //private static final Logger logger = Logger.getLogger(FileUtils.class);

    private static final int BUFFER = 1024;

    /**
     * 功 能: 拷贝文件(只能拷贝文件)
     *
     * @param strSourceFileName
     *            指定的文件全路径名
     * @param strDestDir
     *            拷贝到指定的文件夹
     * @return 如果成功true;否则false
     */
    public boolean copyTo(String strSourceFileName, String strDestDir) {
        File fileSource = new File(strSourceFileName);
        File fileDest = new File(strDestDir);

        // 如果源文件不存或源文件是文件夹
        if (!fileSource.exists() || !fileSource.isFile()) {
            log.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
            return false;
        }

        // 如果目标文件夹不存在
        if (!fileDest.isDirectory() || !fileDest.exists()) {
            if (!fileDest.mkdirs()) {
                log.debug("目录文件夹不存，在创建目标文件夹时失败!");
                return false;
            }
        }

        try {
            String strAbsFilename = strDestDir + File.separator + fileSource.getName();

            FileInputStream fileInput = new FileInputStream(strSourceFileName);
            FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

            log.debug("开始拷贝文件:");

            int count = -1;

            long nWriteSize = 0;
            long nFileSize = fileSource.length();

            byte[] data = new byte[BUFFER];

            while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

                fileOutput.write(data, 0, count);

                nWriteSize += count;

                long size = (nWriteSize * 100) / nFileSize;
                long t = nWriteSize;

                String msg = null;

                if (size <= 100 && size >= 0) {
                    msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
                    log.debug(msg);
                } else if (size > 100) {
                    msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
                    log.debug(msg);
                }

            }

            fileInput.close();
            fileOutput.close();

            log.debug("拷贝文件成功!");
            return true;

        } catch (Exception e) {
            log.debug("异常信息：[");
            log.error(e.getMessage().toString());
            log.debug("]");
            return false;
        }
    }

    /**
     * 删除指定的文件
     *
     * @param strFileName
     *            指定绝对路径的文件名
     * @return 如果删除成功true否则false
     */
    public boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);

        if (!fileDelete.exists() || !fileDelete.isFile()) {
            log.debug("错误: " + strFileName + "不存在!");
            return false;
        }

        return fileDelete.delete();
    }

    /**
     * 移动文件(只能移动文件)
     *
     * @param strSourceFileName
     *            是指定的文件全路径名
     * @param strDestDir
     *            移动到指定的文件夹中
     * @return 如果成功true; 否则false
     */
    public boolean moveFile(String strSourceFileName, String strDestDir) {
        if (copyTo(strSourceFileName, strDestDir)) {
            return this.delete(strSourceFileName);
        }
        else {
            return false;
        }
    }

    /**
     * 创建文件夹
     *
     * @param strDir
     *            要创建的文件夹名称
     * @return 如果成功true;否则false
     */
    public static boolean makedir(String strDir) {
        File fileNew = new File(strDir);

        if (!fileNew.exists()) {
            log.info("文件夹不存在--创建文件夹");
            return fileNew.mkdirs();
        } else {
            log.debug("文件夹存在");
            return true;
        }
    }

    /**
     * 删除文件夹
     *
     * @param strDir
     *            要删除的文件夹名称
     * @return 如果成功true;否则false
     */
    public boolean rmdir(String strDir) {
        File rmDir = new File(strDir);
        if (rmDir.isDirectory() && rmDir.exists()) {
            String[] fileList = rmDir.list();

            for (int i = 0; i < fileList.length; i++) {
                String subFile = strDir + File.separator + fileList[i];
                File tmp = new File(subFile);
                if (tmp.isFile()) {
                    tmp.delete();
                }
                else if (tmp.isDirectory()) {
                    rmdir(subFile);
                }
                else {
                    log.debug("error!");
                }
            }
            rmDir.delete();
        } else {
            return false;
        }
        return true;
    }

    /**
     * 函数功能说明
     * @author zhenglb
     * @date 2016-5-20 17:21:14
     * @Title: downFile
     * @Description: TODO:下载文件
     * @param @param path
     * @param @param response
     * @param @param allPath
     * @param @throws FileNotFoundException
     * @param @throws IOException
     * @param @throws UnsupportedEncodingException    设定文件
     * @return void    返回类型
     * @throws
     */
    public static void downFile(String path, HttpServletResponse response, String allPath )
            throws FileNotFoundException, IOException, UnsupportedEncodingException
    {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;
        File uploadFile = new File(allPath);
        fis = new FileInputStream(uploadFile);
        bis = new BufferedInputStream(fis);
        fos = response.getOutputStream();
        bos = new BufferedOutputStream(fos);
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode(path, "utf-8"));
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush();
        fis.close();
        bis.close();
        fos.close();
        bos.close();
    }
    /**
     * 函数功能说明
     * @author zhenglb
     * @date 2016-5-20 17:21:14
     * @Title: saveFile
     * @Description: TODO:保存文件
     * @param @param is
     * @param @param uploadedFileLocation
     * @param @throws FileNotFoundException
     * @param @throws IOException
     * @param @throws UnsupportedEncodingException    设定文件
     * @return File 返回类型
     * @throws
     */
    public static File saveFile(InputStream is, String uploadedFileLocation) {
        // TODO Auto-generated method stub
        File file = new File(uploadedFileLocation);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((is.read(buffer)) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存文件失败：" + e.getMessage(), e);
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("文件关闭失败：" + e.getMessage(), e);
            }
        }
        System.out.println(uploadedFileLocation+"文件大小"+file.length());
        if (file.length()<5) {
            file.delete();
            return null;
        }
        return file;
    }

    public static String processFileName(String fileNameInput) {
        String fileNameOutput = null;
        fileNameOutput = fileNameInput.substring(
                fileNameInput.lastIndexOf("\\") + 1, fileNameInput.length());
        return fileNameOutput;
    }

    /**
     * 创建文件
     *
     * @param destFileName
     * @return
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            log.info("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            log.info("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            log.info("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                log.info("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                log.info("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                log.info("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.info("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }
    
    //读取JSON文件To字符串
    public static String ReadFile(String path) throws IOException{
        StringBuffer sb = new StringBuffer();
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    //写文件
    public static void WriteFile(String path, String content) throws IOException{
        Clear(path);//清空文本内容
        File f = new File(path);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
        bw.write(content);
        bw.close();
    }

    public static void Clear(String path) throws IOException{
        File f = new File(path);
        FileWriter fw =  new FileWriter(f);
        fw.write("");
        fw.close();
    }

}
