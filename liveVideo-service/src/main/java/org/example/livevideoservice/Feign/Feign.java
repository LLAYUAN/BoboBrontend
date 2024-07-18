package org.example.livevideoservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "gateway")
public interface Feign {
    @PostMapping("/internal/user/setStatus")
    void setStatus(@RequestBody Map<String, Object> requestBody);

    @DeleteMapping("/internal/message/delete/{roomID}")
    void deleteRoomMessages(@PathVariable Integer roomID);
}
