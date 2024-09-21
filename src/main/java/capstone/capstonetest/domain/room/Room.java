package capstone.capstonetest.domain.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Room {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;

}
