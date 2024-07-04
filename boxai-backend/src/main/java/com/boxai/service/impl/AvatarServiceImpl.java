package com.boxai.service.impl;
import com.boxai.mapper.AvatarMapper;
import com.boxai.model.entity.Avatar;
import com.boxai.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class AvatarServiceImpl implements AvatarService {
    @Autowired
    private AvatarMapper avatarMapper;
    @Autowired
    private ResourceLoader resourceLoader;
    @Override
    public void saveAvatar(MultipartFile file) throws Exception {

        try {
            //创建一个avatar对象来保存头像信息
            Avatar avatar = new Avatar();
            avatar.setFileName(file.getOriginalFilename());
            avatar.setData(file.getBytes());
//            avatarMapper.insertAvatar(avatar);


            String resourcePath = resourceLoader.getResource("classpath:").getURI().getPath();
                File imageDir = new File(resourcePath + "image");
                if (!imageDir.exists()){
                    imageDir.mkdir();
                }
                File outputFile = new File(imageDir,file.getOriginalFilename());
                try
                        (OutputStream os = new FileOutputStream(outputFile)) {
                    os.write(file.getBytes());
                }
        } catch (IOException e) {
            throw new Exception("Failed to store file", e);
        }

    }

}
