package com.qinyuan15.utils.image;

import com.google.common.collect.Lists;
import com.qinyuan15.utils.concurrent.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Download images by multi thread
 * Created by qinyuan on 15-5-11.
 */
public class MultiImageDownloader {

    public final static int DEFAULT_THREAD_SIZE = 6;

    private final static Logger LOGGER = LoggerFactory.getLogger(MultiImageDownloader.class);

    private ImageDownloader imageDownloader;
    private List<String> urls = new ArrayList<>();
    private int threadSize = DEFAULT_THREAD_SIZE;

    public MultiImageDownloader(ImageDownloader imageDownloader) {
        this.imageDownloader = imageDownloader;
    }

    public MultiImageDownloader clearUrls() {
        urls.clear();
        return this;
    }

    public MultiImageDownloader addUrl(String url) {
        urls.add(url);
        return this;
    }

    public MultiImageDownloader setThreadSize(int threadSize) {
        this.threadSize = threadSize;
        return this;
    }

    public List<String> download() {
        Queue<String> paths = new ConcurrentLinkedQueue<>();
        ThreadPool threadPool = new ThreadPool(threadSize);
        for (String url : this.urls) {
            threadPool.add(new DownloadThread(url, paths));
        }
        threadPool.waitToComplete();
        return Lists.newArrayList(paths);
    }

    private class DownloadThread extends Thread {
        String imageUrl;
        Queue<String> paths;

        DownloadThread(String imageUrl, Queue<String> paths) {
            this.imageUrl = imageUrl;
            this.paths = paths;
        }

        @Override
        public void run() {
            try {
                String path = imageDownloader.save(imageUrl);
                if (StringUtils.hasText(path)) {
                    paths.add(path);
                }
            } catch (Exception e) {
                LOGGER.error("fail to download {}: {}", imageUrl, e);
            }
        }
    }
}
