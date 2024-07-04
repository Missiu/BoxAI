package com.boxai.service;

import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {
    void saveAvatar(MultipartFile file) throws Exception;
}
