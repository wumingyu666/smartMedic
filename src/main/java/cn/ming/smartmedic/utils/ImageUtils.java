package cn.ming.smartmedic.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class ImageUtils {

    public static String readImageFromNet(String imageUrl) throws IOException {
        int lastIndex = imageUrl.lastIndexOf("/");
        // 截取文件名部分
        String destinationFile = "tmp/image/" + imageUrl.substring(lastIndex + 1);
        try {
            // 打开指定的 URL，并获取其输入流
            URL url = new URL(imageUrl);
            BufferedInputStream inputStream = new BufferedInputStream(url.openStream());

            // 创建输出流，写入数据到本地文件
            FileOutputStream outputStream = new FileOutputStream(new File(destinationFile));

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // 关闭流
            inputStream.close();
            outputStream.close();

            System.out.println("Successfully downloaded image from " + imageUrl);
        } catch (IOException e) {
            log.error("read image from met failed,error:{}", e.getMessage());
            throw e;
        }

        return destinationFile;
    }

    public static void main(String[] args) throws IOException {
        ImageUtils utils = new ImageUtils();
        String s = utils.readImageFromNet("https://copyright.bdstatic.com/vcg/creative/cc9c744cf9f7c864889c563cbdeddce6.jpg");
        System.out.println(s);
    }
}
