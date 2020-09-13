package com.example.emotions.util;

import com.baidu.aip.face.AipFace;
import com.example.emotions.vo.FaceInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class BApiUtil {
    private static final String APP_ID = "17791699";
    private static final String API_KEY = "kXUlAzg8UvGdbD4OT7AYbetg";
    private static final String SECRET_KEY = "EpB4CUaiVxE9Go5rQ1qLseFCBpgfY4Wb";

    private static AipFace client = null;

    public static AipFace getClient() {
        if (client == null) {
            client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        }
        return client;
    }

    public static FaceInfo processFace(byte[] data) {
        FaceInfo faceInfo = new FaceInfo();
        AipFace faceClient = getClient();
        String img = Base64Util.encode(data);
        HashMap<String, String> opts = new HashMap<>();
        opts.put("face_field", "expression,emotion");
        JSONObject res = faceClient.detect(img, "BASE64", opts);
        JSONObject result = res.optJSONObject("result");
        if (result != null) {
            int faceNum = result.optInt("face_num", 0);
            if (faceNum > 0) {
                JSONArray faceList = result.getJSONArray("face_list");
                JSONObject face = faceList.optJSONObject(0);
                faceInfo.setExpression(face.getJSONObject("expression").getString("type"));
                faceInfo.setEmotion(face.getJSONObject("emotion").getString("type"));
            }
        }
        return faceInfo;
    }
}
