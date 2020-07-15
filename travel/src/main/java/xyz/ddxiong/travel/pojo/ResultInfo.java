package xyz.ddxiong.travel.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 封装返回的结果信息
 * @author Lenovo
 */
public class ResultInfo {
    private boolean flag; //表示服务器的执行结果
    private String errorMsg; // 存放错误信息
    private Object successData; // 存放执行成功后,需要返回的数据信息

    /**
     * 将java对象转json字符串
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String jsonStr = om.writeValueAsString(obj);
        return jsonStr;
    }


    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", errorMsg='" + errorMsg + '\'' +
                ", successData=" + successData +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getSuccessData() {
        return successData;
    }

    public void setSuccessData(Object successData) {
        this.successData = successData;
    }
}
