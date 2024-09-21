package capstone.capstonetest.domain.room;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomRepository {
    private final SqlSessionTemplate sql;

    public List<Room> findAllRooms(){
        return sql.selectList("Room.findAllRooms");
    }

    public void addRoom(Room room) {
        sql.insert("Room.addRoom",room);
    }

    public Long findIdByAddress(String address){
        return sql.selectOne("Room.findIdByAddress",address);
    }

    public Room findRoomById(Long roomId){
        return sql.selectOne("Room.findRoomById", roomId);
    }
}
