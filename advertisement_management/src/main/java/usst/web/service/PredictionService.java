package usst.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class PredictionService {
    @Value("${python.path}")
    private String pythonPath;

    public String predict(String jsonInput) {
        // 将JSON输入转换为numpy数组格式的字符串
        String numpyArrayStr = convertToJsonArrayString(jsonInput);

        // 调用Python脚本
        Process proc;
        StringBuilder output = new StringBuilder();
        try {
            proc = Runtime.getRuntime().exec("python " + pythonPath + " " + numpyArrayStr);
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                output.append(line).append("\n");
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "{\"code\": 500, \"message\": \"Internal Server Error\", \"data\": {}}";
        }

        // 返回预测结果
        return "{\"code\": 200, \"message\": \"Prediction successful\", \"data\": " + output.toString() + "}";
    }

    private String convertToJsonArrayString(String jsonInput) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 假设jsonInput是用户信息的数组
            Object[] users = mapper.readValue(jsonInput, Object[].class);
            // 转换为numpy数组格式的字符串
            StringBuilder numpyArrayStr = new StringBuilder();
            numpyArrayStr.append("[");
            for (Object user : users) {
                numpyArrayStr.append(mapper.writeValueAsString(user));
                numpyArrayStr.append(",");
            }
            if (numpyArrayStr.length() > 1) {
                numpyArrayStr.setLength(numpyArrayStr.length() - 1);
            }
            numpyArrayStr.append("]");
            return numpyArrayStr.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}