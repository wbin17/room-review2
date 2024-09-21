package capstone.capstonetest.web;

import capstone.capstonetest.service.KakaoAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressController {

    private final KakaoAddressService kakaoAddressService;

    @GetMapping("/searchAddress")
    @ResponseBody
    public List<String> searchAddress(@RequestParam String query) {
        return kakaoAddressService.searchAddress(query);
    }
}
