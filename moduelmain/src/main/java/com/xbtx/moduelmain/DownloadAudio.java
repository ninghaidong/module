package com.xbtx.moduelmain;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.X509TrustManager;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: 宁
 * @className:
 * @date: 2021/9/22 11:18
 */
public class DownloadAudio {
    private File file;

    public String savePath; // 储存下载文件的路径

    private List<String> audiolist = new ArrayList<>();//获取接口的数据

    private List<String> audioNamelist = new ArrayList<>();//获取本地已下载的文件名

    public DownloadAudioListener downloadAudioListener;

    public DownloadAudio(DownloadAudioListener downloadAudioListener, String savePath, List<String> audiolist) {

        this.downloadAudioListener = downloadAudioListener;

        this.savePath = savePath;

        this.audiolist = audiolist;

        audioNamelist.clear();

        audioNamelist = getFilesAllName(savePath);//获取本地已下载的文件名

    }

    /**
     * @author : Code23
     * @time : 2020/11/12
     * @name : isDownloadAudio
     * @Parameters : [url, i]
     * @describe : 判断是否已经下载过，已经下载的返回路径，没下载的继续下载
     */

    public void isDownloadAudio(final String url, final int i) {

        if (audioNamelist != null) {

            if (audioNamelist.size() > 0) {

                for (int t = 0; t < audioNamelist.size(); t++) {

                    if (audiolist.get(i).equals(audioNamelist.get(t))) {

                        Log.e("DownloadAudio", "下载了");
                        downloadAudioListener.DownloadSuccess(savePath + "/" + audioNamelist.get(i), i);

                        break;

                    } else {

                        if (t == audiolist.size() - 1) {
                            Log.e("DownloadAudio", "没下载");

                            OkHttpDownloadAudio(url, i);

                        }

                    }

                }

            } else {

                OkHttpDownloadAudio(url, i);

            }

        } else {

            OkHttpDownloadAudio(url, i);

        }

    }
    //定义一个信任所有证书的TrustManager
    final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };
    @SuppressLint("NewApi")

    public void OkHttpDownloadAudio(final String url, final int i) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //https验证
        builder.sslSocketFactory(new SSL(trustAllCert), trustAllCert);
        OkHttpClient okHttpClient = builder.build();
        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //请求失败

                downloadAudioListener.DownloadFailure("failure", i);
            }


            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //请求成功

                InputStream inputStream = null;

                FileOutputStream fileOutputStream = null;

                byte[] bytes = new byte[1024 * 10];

                int length = 0;

                file = new File(savePath);

                if (!file.exists()) { //文件夹不存在

                    // 创建文件夹

                    file.mkdirs();

                }

                try {

                    inputStream = response.body().byteStream();

                    file = new File(savePath, audiolist.get(i));

                    fileOutputStream = new FileOutputStream(file);

                    while ((length = inputStream.read(bytes)) != -1) {

                        fileOutputStream.write(bytes, 0, length);

                    }

                    fileOutputStream.flush();

                    //下载成功

                    downloadAudioListener.DownloadSuccess(file.getPath(), i);

                } catch (Exception e) {

                    e.printStackTrace();

                    // 下载失败

                    downloadAudioListener.DownloadFailure("failure", i);

                } finally {

                    if (inputStream != null) {

                        inputStream.close();

                    }

                    if (fileOutputStream != null) {

                        fileOutputStream.close();

                    }

                }

            }

        });

    }

    public interface DownloadAudioListener {

        void DownloadSuccess(String audiourl, int i);//成功返回

        void DownloadFailure(String audiourl, int i);//失败返回

    }

    /**
     * 获取文件夹所有文件名
     *
     * @param path
     * @return
     */

    public List<String> getFilesAllName(String path) {

        File file = new File(path);

        if (!file.exists()) { //文件夹不存在

            // 创建文件夹

            file.mkdirs();

        }

        File[] files = file.listFiles();

        if (files == null) {//空目录

            return null;

        }

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {

            stringList.add(files[i].getName());

        }

        return stringList;

    }

}