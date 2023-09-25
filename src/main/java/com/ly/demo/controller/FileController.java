package com.ly.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.ly.demo.utils.MyFileUtil;
import com.ly.demo.vo.ZipVO;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author liuyang
 * @Date 2023/9/18 11:08
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    @GetMapping("/zip")
    public ResponseEntity<Resource> download02() throws IOException {
        File file01 = FileUtil.newFile("/Users/liuyang/IdeaProjects/demo/src/main/resources/202301.text");
        File file02 = FileUtil.newFile("/Users/liuyang/IdeaProjects/demo/src/main/resources/202302.text");
        HttpResponse response = HttpUtil.createGet("https://apaas-debug.uban360.com/unified-login/imgs/logo.png").execute();
        byte[] bytes = response.bodyBytes();
        File file03 = FileUtil.writeBytes(bytes, "aaaaaaaaaaaaaaa.png");
        File file = new File("tmp.zip");
        File[] srcFiles = new File[]{
                file01,
                file02,
                file03
        };
        File zip = ZipUtil.zip(file, false, srcFiles);
        Resource resource = new UrlResource(zip.toURI());
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = UriUtils.encode("考勤.zip", "UTF-8"); // 进行URL编码
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedFileName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/test01")
    public ResponseEntity<Resource> download02(HttpServletResponse response) throws IOException {
        String url = "https://apaas-debug.uban360.com/unified-login/imgs/logo.png";
        HttpResponse httpResponse = HttpUtil.createGet(url).execute();
        byte[] bytes = httpResponse.bodyBytes();
        File file03 = FileUtil.writeBytes(bytes, "aaa.png");
        String encodedFileName = UriUtils.encode("大牛逼.text", "UTF-8"); // 进行URL编码
        Resource resource = new UrlResource(file03.toURI());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedFileName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/test02")
    public ResponseEntity<Resource> test02() throws IOException {
        File file01 = FileUtil.newFile("/Users/liuyang/IdeaProjects/demo/src/main/resources/202301.text");
        File file02 = FileUtil.newFile("/Users/liuyang/IdeaProjects/demo/src/main/resources/202302.text");
        HttpResponse response = HttpUtil.createGet("https://apaas-debug.uban360.com/unified-login/imgs/logo.png").execute();
        byte[] bytes = response.bodyBytes();

        File file03 = FileUtil.writeBytes(bytes, "aaaaaaaaaaaaaaa.png");
        File[] srcFiles = new File[]{
                file01,
                file02,
                file03
        };

        // 创建一个临时zip文件
        File zipFile = File.createTempFile("temp", ".zip");
        this.zip(zipFile, srcFiles);
        Resource resource = new UrlResource(zipFile.toURI());
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = UriUtils.encode("考勤.zip", "UTF-8"); // 进行URL编码
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedFileName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/test04")
    public ResponseEntity<Resource> test04() throws IOException {
        HttpResponse response = HttpUtil.createGet("https://apaas-debug.uban360.com/unified-login/imgs/logo.png").execute();
        byte[] bytes = response.bodyBytes();
        // 创建一个临时文件
        File tempFile = new File("一年级.text");

        // 将字节数组写入文件
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(bytes);
        fos.close();
        File[] srcFiles = new File[]{
                tempFile
        };

        // 创建一个临时zip文件
        File zipFile = File.createTempFile("temp", ".zip");
        this.zip(zipFile, srcFiles);
        Resource resource = new UrlResource(zipFile.toURI());
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = UriUtils.encode("考勤.zip", "UTF-8"); // 进行URL编码
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedFileName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/test03/{text}")
    public void test03(@PathVariable String text, HttpServletResponse response) throws Exception {
        File file = MyFileUtil.generateFormatWord(text);
        String encodedFileName = UriUtils.encode("tmp.docx", "UTF-8"); // 进行URL编码
        // 设置响应头
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + encodedFileName);

        // 获取文件内容并写入输出流
        try (InputStream inputStream = new FileInputStream(file)) {
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        }
    }

    private static void zip(ZipOutputStream zos, List<ZipVO> zipVOS) throws IOException {
        for (ZipVO zipVO : zipVOS) {
            ZipEntry entry = new ZipEntry(zipVO.getName());
            zos.putNextEntry(entry);
            zos.write(zipVO.getContent());
            zos.closeEntry();
        }
    }


    private void zip(File file, File[] srcFiles) throws IOException {
        // 创建ZipOutputStream来将文件写入zip文件
        try (FileOutputStream fos = new FileOutputStream(file);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (File srcFile : srcFiles) {
                if (srcFile.exists()) {
                    // 添加每个文件到zip文件中
                    FileInputStream fis = new FileInputStream(srcFile);
                    ZipEntry zipEntry = new ZipEntry(srcFile.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, bytesRead);
                    }
                    zos.closeEntry();
                    fis.close();
                }
            }
        }
    }

    @GetMapping("/test07")
    public ResponseEntity<Resource> test07() throws IOException {
        File file01 = FileUtil.newFile("/Users/liuyang/IdeaProjects/demo/src/main/resources/202301.text");
        File file02 = FileUtil.newFile("/Users/liuyang/IdeaProjects/demo/src/main/resources/202302.text");
        ArrayList<File> objects = new ArrayList<>();
        objects.add(file01);
        objects.add(file02);
        File[] srcFiles = new File[objects.size()];
        objects.toArray(srcFiles);
        File file = File.createTempFile("tmp", ".zip");
        zip(file, srcFiles);
        Resource resource = new UrlResource(file.toURI());
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = UriUtils.encode("考勤.zip", "UTF-8"); // 进行URL编码
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedFileName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
