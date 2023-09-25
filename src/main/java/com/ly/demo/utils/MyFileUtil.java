package com.ly.demo.utils;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author liuyang
 * @Date 2023/9/23 16:28
 **/
@Slf4j
public class MyFileUtil {

    public static void zip(File file, File[] srcFiles) throws IOException {
        // 创建ZipOutputStream来将文件写入zip文件
        try (FileOutputStream fos = new FileOutputStream(file);
             ZipOutputStream zos = new ZipOutputStream(fos, StandardCharsets.UTF_8)) {

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

    public static File generatePdf(String text) throws DocumentException {
        // 创建一个字节数组输出流，用于存储生成的 PDF 数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 创建一个新的文档
        Document document = new Document();
        // 创建 PdfWriter，并将文档与输出流关联
        PdfWriter.getInstance(document, byteArrayOutputStream);
        // 打开文档
        document.open();
        // 添加文本内容到文档
        document.add(new Paragraph(text));

        // 关闭文档
        document.close();
        byte[] pdfData = byteArrayOutputStream.toByteArray();
        File file = cn.hutool.core.io.FileUtil.writeBytes(pdfData, "tmp.pdf");
        return file;
    }

    /**
     * @param content
     * @return
     * @throws IOException
     */
    public static File generateFormatWord(String content) throws IOException {
        log.info("MyFileUtil.generateFormatWord param - content:{}", content);
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph currentParagraph = null;
        XWPFRun run = null;

        String[] paragraphs = content.split("\\n");

        for (int i = 0; i < paragraphs.length; i++) {
            String paragraphText = paragraphs[i];

            if (i == 0) {
                // 第一个段落作为标题，设置居中和粗体
                currentParagraph = doc.createParagraph();
                currentParagraph.setAlignment(ParagraphAlignment.CENTER);
                run = currentParagraph.createRun();
                run.setBold(true);
            } else {
                // 其他段落
                currentParagraph = doc.createParagraph();
                run = currentParagraph.createRun();
            }

            run.setText(paragraphText);
        }

        File file = FileUtil.createTempFile("tmp", ".docx", true);
        FileOutputStream out = new FileOutputStream(file);
        doc.write(out);
        out.close();
        return file;
    }

}
