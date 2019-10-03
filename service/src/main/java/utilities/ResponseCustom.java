package utilities;

import javax.ws.rs.core.Response;

public class ResponseCustom {

    public static Response build(){
        return Response.noContent()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .build();
    }

    public static Response build(int status, Object entity){
        return Response
                .status(status)
                .entity(entity)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
//                .header("Access-Control-Max-Age", "151200")
//                .header("Access-Control-Allow-Headers", "x-requested-with,Content-Type")
                .build();
    }

    public static Response build(int status, Object entity, int total){
        return Response
                .status(status)
                .entity(entity)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .header("Access-Control-Expose-Headers", "*")
                .header("X-Total-Count", total)
                .build();
    }
}