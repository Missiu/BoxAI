package com.boxai.controller;

import com.boxai.common.base.R;
import com.boxai.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    @Autowired
    private AvatarService avatarService;
    @PostMapping("/upload")
    public R<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            avatarService.saveAvatar(file);
            return R.ok("1111");
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}
