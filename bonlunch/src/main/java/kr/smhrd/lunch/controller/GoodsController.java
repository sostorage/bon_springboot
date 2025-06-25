package kr.smhrd.lunch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import kr.smhrd.lunch.dto.GoodsDTO;
import kr.smhrd.lunch.entity.Goods;
import kr.smhrd.lunch.service.GoodsService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class GoodsController {

    private final WebMvcConfigurer corsConfigurer;
	
	@Autowired
	GoodsService goodsService;

    GoodsController(WebMvcConfigurer corsConfigurer) {
        this.corsConfigurer = corsConfigurer;
    }
	
	// 1단계 전달하기
	@GetMapping("/goods_list")
	public List<GoodsDTO> getGoodsList(GoodsDTO goodsDTO, Model model) {
		System.out.println("[list 출력 컨트롤러]");
		
		List<GoodsDTO> list = goodsService.getGoodsList();
		
		
		return list;
//		return goodsService.getGoodsDetail(); => return 타입을 list로 돌려주기
		// 2단계 Service -> Repository
		
	}
	
	// 상세페이지 눌렀을 때 url 이클립스 콘솔에 출력하기
	@GetMapping("/goods_list/{id}")
	public GoodsDTO getGoodsDetail(@PathVariable("id") int id) {
		
		GoodsDTO goodsDetail = goodsService.getGoodsDetail(id);
		System.out.println("번호 확인:"+ id);
		
		return goodsDetail;
	}
}
