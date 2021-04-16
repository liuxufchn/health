package com.shaffer.health.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.shaffer.health.constant.MessageConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @program: health
 * @Date: 2021/4/15 19:08
 * @Author: Shaffer
 * @Description:
 */
public class QiNiuUtil {

    public static final String BUCKET;
    public static final String ACCESSKEYID;
    public static final String ACCESSKEYSECRET;
    public static final String DOMAIN;

    static {
        // 初始化配置参数
        ResourceBundle resource = ResourceBundle.getBundle("qiniu");
        BUCKET = resource.getString("bucket");
        ACCESSKEYID = resource.getString("accesskeyid");
        ACCESSKEYSECRET = resource.getString("accesskeysecret");
        DOMAIN = resource.getString("domain");
    }

    /**
     * 遍历7牛上的所有图片
     * @return 图片列表
     */
    public static List<String> listFile(){
        BucketManager bucketManager = getBucketManager();

        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;

        //列举空间文件列表, 第一个参数：图片的仓库（空间名）,第二个参数，文件名前缀过滤。“”代理所有
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(BUCKET,"", limit, "");
        List<String> imageFiles = new ArrayList<>();
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                // item.key 文件名
                imageFiles.add(item.key);
                System.out.println(item.key);
            }
        }
        return imageFiles;
    }

    /**
     * 批量删除
     *
     * @param filenames 需要删除的文件名列表
     * @return 删除成功的文件名列表
     */
    public static List<String> removeFiles(String... filenames) {
        // 删除成功的文件名列表
        List<String> removeSuccessList = new ArrayList<String>();
        if (filenames.length > 0) {
            try {
                // 创建仓库管理器
                BucketManager bucketManager = getBucketManager();
                BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
                batchOperations.addDeleteOp(BUCKET, filenames);
                Response response = bucketManager.batch(batchOperations);
                BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
                for (int i = 0; i < filenames.length; i++) {
                    BatchStatus status = batchStatusList[i];
                    String key = filenames[i];
                    System.out.print(key + "\t");
                    if (status.code == 200) {
                        System.out.println("delete success");
                    } else {
                        System.out.println(status.data.error);
                    }
                }
            }
            catch (QiniuException e) {
                e.printStackTrace();
                throw new RuntimeException(MessageConstant.PIC_UPLOAD_FAIL);
            }
        }
        return removeSuccessList;
    }

    /**
     * 上传文件
     *
     * @param localFilePath 文件路径
     * @param savedFilename 文件名
     */
    public static void uploadFile(String localFilePath, String savedFilename) {
        UploadManager uploadManager = getUploadManager();
        String upToken = getToken();
        try {
            Response response = uploadManager.put(localFilePath, savedFilename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.printf("key=%s, hash=%s%n", putRet.key, putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            throw new RuntimeException(MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    /**
     * 上传文件
     *
     * @param bytes         字节流
     * @param savedFilename 保存的文件名
     */
    public static void uploadViaByte(byte[] bytes, String savedFilename) {
        UploadManager uploadManager = getUploadManager();
        String upToken = getToken();
        try {
            Response response = uploadManager.put(bytes, savedFilename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            throw new RuntimeException(MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    private static UploadManager getUploadManager() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //构建上传管理器
        return new UploadManager(cfg);
    }

    private static String getToken() {
        // 创建授权
        Auth auth = Auth.create(ACCESSKEYID, ACCESSKEYSECRET);
        // 获得认证后的令牌
        return auth.uploadToken(BUCKET);
    }

    private static BucketManager getBucketManager() {
        // 创建授权信息
        Auth auth = Auth.create(ACCESSKEYID, ACCESSKEYSECRET);
        // 创建操作某个仓库的管理器
        return new BucketManager(auth, new Configuration(Region.region2()));
    }
}
