package capstone.capstonetest.service;

import capstone.capstonetest.domain.room.Room;
import capstone.capstonetest.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Long addRoom(Room room){
        roomRepository.addRoom(room);
        return room.getId();
    }

    public List<Room> roomList(){
        return roomRepository.findAllRooms();
    }

    public Long findIdByAddress(String address){
        return roomRepository.findIdByAddress(address);
    }


}
