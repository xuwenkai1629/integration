package com.integration.minio;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.file.FileReader;
import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;

/**
 * 描述
 * Title: ContentTypeUtil
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/14
 */
public class ContentTypeUtil {
    private static JSONObject contentType;

    static {
        FileReader fileReader = new FileReader("Content-Type.json");
        contentType = JSONObject.parseObject(fileReader.readString());
    }

    public static String getContentType(InputStream inputStream) {
        String prefix = FileTypeUtil.getType(inputStream);
        return contentType.get(prefix) == null ? "application/octet-stream" : contentType.get(prefix).toString();
    }
}
