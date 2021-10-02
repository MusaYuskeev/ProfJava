package controllers;

import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import services.storeService;

import java.io.IOException;


@RestController
public class PostController {
    @Autowired
    private storeService storeService;


    @PostMapping("/coords")
    public @ResponseBody
    Response getCoords(@RequestBody PointDTO pointdto) throws IOException {
        storeService.addCoord(pointdto);
        if (pointdto != null) {
            return new Response("ok", true);
        } else {
            return new Response("fail", false);
        }

    }
}