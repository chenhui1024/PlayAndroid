package cn.landi.playandroid.util;

import android.content.Context;
import android.util.Log;

import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.CosXmlSimpleService;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.transfer.COSXMLUploadTask;
import com.tencent.cos.xml.transfer.TransferConfig;
import com.tencent.cos.xml.transfer.TransferManager;
import com.tencent.cos.xml.transfer.TransferState;
import com.tencent.cos.xml.transfer.TransferStateListener;
import com.tencent.qcloud.core.auth.QCloudCredentialProvider;
import com.tencent.qcloud.core.auth.ShortTimeCredentialProvider;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/2/14
 * @edit TODO
 */
public class TecentTransferHelper {

    private static String APPID;
    private static String REGION;
    private static String SECRETKEY;
    private static String SECRETID;
    private static String BUCKET_NAME;
    private static String COSPATH;

    private static CosXmlServiceConfig serviceConfig;

    static {

        APPID = "1258639602";
        REGION = "ap-beijing";
        SECRETKEY = "Um6CPO9IEvOo05vZzEG6h6Ri7uW5b5Vq";
        SECRETID = "AKID4ydAWfpHkywfEbrbFfspY7WhfOmxncDn";
        BUCKET_NAME = "c992623337-1258639602";
        COSPATH = "AllinpayInFace/";

        serviceConfig = new CosXmlServiceConfig.Builder()
                .setAppidAndRegion(APPID, REGION)
                .setDebuggable(true)
                .builder();
    }

    public void uploadFileLog2Tecent(Context context, String localFilePath) {

    }

    public void uploadFile2Tecent(Context context, String remoteName, String localFilePath) {

        /**
         * 初始化 {@link QCloudCredentialProvider} 对象，来给 SDK 提供临时密钥。
         */
        QCloudCredentialProvider credentialProvider = new ShortTimeCredentialProvider(SECRETID,
                SECRETKEY, 300);

        //初始化 COS 服务类
        CosXmlSimpleService cosXmlService = new CosXmlSimpleService(context, serviceConfig, credentialProvider);

        // 初始化 TransferConfig
        TransferConfig transferConfig = new TransferConfig.Builder().build();

        //初始化 TransferManager
        TransferManager transferManager = new TransferManager(cosXmlService, transferConfig);

        String cosPath = COSPATH + remoteName; //即存储到 COS 上的绝对路径, 格式如 cosPath = "test.txt";
        String uploadId = null; //若存在初始化分片上传的 UploadId，则赋值对应uploadId值用于续传，否则，赋值null。

        //上传文件
        COSXMLUploadTask cosxmlUploadTask = transferManager.upload(BUCKET_NAME, cosPath, localFilePath, uploadId);

        //设置上传进度回调
        cosxmlUploadTask.setCosXmlProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                float progress = 1.0f * complete / target * 100;
                Log.e("TEST",  String.format("progress = %d%%", (int)progress));
            }
        });
        //设置返回结果回调
        cosxmlUploadTask.setCosXmlResultListener(new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.e("TEST",  "Success: " + result.printResult());
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.e("TEST",  "Failed: " + (exception == null ? serviceException.getMessage() : exception.toString()));
            }
        });
        //设置任务状态回调, 可以查看任务过程
        cosxmlUploadTask.setTransferStateListener(new TransferStateListener() {
            @Override
            public void onStateChanged(TransferState state) {
                Log.e("TEST", "Task state:" + state.name());
            }
        });

    }

}
