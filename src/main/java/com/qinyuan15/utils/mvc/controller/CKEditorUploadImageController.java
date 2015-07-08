package com.qinyuan15.utils.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Controller to save upload image from CKEditor
 * Created by qinyuan on 15-7-7.
 */
@Controller
public class CKEditorUploadImageController extends ImageController {
    @RequestMapping("ckeditor-image-upload")
    public void index(HttpServletResponse response,
                      @RequestParam(value = "upload", required = true) MultipartFile upload) throws Exception {
        PrintWriter out = response.getWriter();

        //String uploadContentType = request.getContentType();
        /*Set<String> contentTypes = Sets.newHashSet("image/pjpeg", "image/jpeg", "image/png", "image/x-png", "image/gif", "image/bmp");
        if (!contentTypes.contains(uploadContentType)) {
            out.print(getResultContent("", "Invalid file format(must be .jpg/.gif/.bmp/.png"));
            return;
        }*/

        if (upload.getSize() > 600 * 1024) {
            out.print(getResultContent("", "File must not greater than 600k"));
            return;
        }

        String saveFilePath = transferUploadFile(upload, "ckeditor-upload");
        String imageUrl = pathToUrl(saveFilePath);
        out.print(getResultContent(imageUrl, ""));
    }

    private String getResultContent(String imageUrl, String prompt) {
        String callback = request.getParameter("CKEditorFuncNum");
        String content = "<script type=\"text/javascript\">";
        content += "window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageUrl + "','" + prompt + "')";
        content += "</script>";
        return content;
    }
}
