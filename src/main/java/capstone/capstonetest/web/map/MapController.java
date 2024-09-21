package capstone.capstonetest.web.map;

import capstone.capstonetest.domain.room.Room;
import capstone.capstonetest.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapController {
    private final RoomService roomService;

    @GetMapping("/map")
    public String showMap(Model model){
        List<Room> rooms = roomService.roomList();

        Room room = rooms.get(0);

        log.info("room = = {}",rooms.get(0));
        model.addAttribute("rooms",rooms);
        model.addAttribute("room",room);
        return "/map/map";
    }
}
