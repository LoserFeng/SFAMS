package com.a02.sfams.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface SystemService {
    String upload(MultipartFile file);
}
