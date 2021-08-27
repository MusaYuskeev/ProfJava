package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.storeService;


@RestController
public class PostController {
    @Autowired
    private storeService storeService;

    @PostMapping("/coords")
    public Response getCoords(@RequestBody String pointdto) throws JsonProcessingException {
        storeService.addCoord(pointdto);

        Response response;
        if (pointdto.split(",").length == 6) {
            response = new Response(true);
        } else {
            response = new Response(false);
        }

        return response;
    }
}