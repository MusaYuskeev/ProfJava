package controllers;

import dao.Point;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.storeService;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private storeService storeService;

    @PostMapping("/coords")
    public @ResponseBody
    Response getCoords(@RequestBody PointDTO pointDTO) throws IOException {
        storeService.addCoord(pointDTO);
        if (pointDTO != null) {
            return new Response("ok", true);
        } else {
            return new Response("fail", false);
        }
    }

    @GetMapping("/tracks")
    public List<Point> getCoords(@RequestParam String idAuto, @RequestParam(defaultValue = "1") Integer maxCount) {
        List<Point> queue = storeService.getTracks(idAuto);
        if (maxCount >= queue.size()) {
            return queue;
        } else {
            return queue.subList(0, maxCount);
        }
    }
}