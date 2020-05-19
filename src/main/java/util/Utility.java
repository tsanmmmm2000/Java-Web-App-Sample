package util;

import client.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Utility {
    private static RepositoryFactory repository = new RepositoryFactory();

    public static RepositoryFactory getRepository() {
        return repository;
    }

    public static void writeAjaxResult(HttpServletResponse response, boolean success, String message, Object data) throws IOException {
        writeAjaxResult(response, GenAjaxResult(success, message, data));
    }

    private static void writeAjaxResult(HttpServletResponse response, AjaxResult result) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private static AjaxResult GenAjaxResult(boolean success, String message, Object data) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(success);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
