package com.seu.universe.utils;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

public class FastDFSUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastDFSUtil.class);

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient1 storageClient = null;

    private static void initConfigAndConnect() throws Exception {
        //初始化配置文件
        //ClientGlobal.init(FDFS_CLIENT_FILENAME);
        /** * 1.读取fastDFS客户端配置文件 */
        Resource resource = new ClassPathResource("fdfs_client.conf");
        File file = resource.getFile();
        String configFile = file.getAbsolutePath();
        /** * 2.配置文件的初始化信息 */
        ClientGlobal.init(configFile);
        //连接FastDFS，创建tracker和storage
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    //上传图片
    public static String uploadImage(String path, String extName) {
        String[] strings;
        StringBuilder res = new StringBuilder();
        try {
            initConfigAndConnect();
            strings = storageClient.upload_file(path, extName, null);
            res.append(strings[0]).append("/").append(strings[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public static void download_file(String path, BufferedOutputStream output) throws IOException, Exception {
        //byte[] b = storageClient.download_file(group, path);
        byte[] b = storageClient.download_file1(path);
        try {
            if (b != null) {
                output.write(b);
            }
        } catch (Exception e) {
        } //用户可能取消了下载
        finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 文件的删除方法
     * 返回值：0删除成功
     *
     * @param group       fastdfs的组名
     * @param storagePath 文件的storagePath路径
     * @throws Exception
     * @throws IOException
     */
    public Integer delete_file(String group, String storagePath) throws IOException, Exception {
        return storageClient.delete_file(group, storagePath);
    }
}