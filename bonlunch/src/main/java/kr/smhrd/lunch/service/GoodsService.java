package kr.smhrd.lunch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import kr.smhrd.lunch.dto.GoodsDTO;
import kr.smhrd.lunch.entity.Goods;
import kr.smhrd.lunch.entity.GoodsDetail;
import kr.smhrd.lunch.repository.GoodsDetailRepository;
import kr.smhrd.lunch.repository.GoodsRepository;

@Service
public class GoodsService {

    private final WebMvcConfigurer corsConfigurer;
	
	// 특정 컴포넌트를 찾지 못했을 때 확인할 3가지
	// 1. 컴포넌트 생성이 잘 되어있는지
	// 2. AutoWired 작성이 되어있는지
	// 3. 어노테이션 작성이 되었는지
	
	@Autowired
	GoodsRepository goodsRepository;
	
	@Autowired
	GoodsDetailRepository goodsDetailRepository;

    GoodsService(WebMvcConfigurer corsConfigurer) {
        this.corsConfigurer = corsConfigurer;
    }
	
	public List<GoodsDTO> getGoodsList() {
		
		// 1. 데이터 처리
		// 2. repository 호출
		List<Goods> result = goodsRepository.findAll();
		// 3. Controller 돌려줄 데이터 처리
		List<GoodsDTO> list = new ArrayList<GoodsDTO>();
		
		for(Goods g : result) {
			GoodsDTO dto = new GoodsDTO();
			dto.setId(g.getId());
			dto.setName(g.getName());
			dto.setPrice(g.getPrice());
			dto.setIsNew(g.getIsNew());
			dto.setIsBest(g.getIsBest());
			dto.setMain_thumb(g.getMain_thumb());
			list.add(dto);
		}
		
		// findAll: List 형태로 DB에 담긴 내용 모두 가져옴
//		return goodsRepository.findAll();
		return list;
		
	}

	public GoodsDTO getGoodsDetail(int id) {
		
		// 1. id값을 가지고 제품 정보 Goods 가지고 오기
		Goods goods = goodsRepository.findById(id).orElse(null);
		GoodsDetail detail = goodsDetailRepository.findById(id).orElse(null);
		 
		 // 2. 두 변수의 값이 null인 경우에는 null을 return 
		if(goods == null || detail == null) {
			return null;
		}
		 
		GoodsDTO dto = new GoodsDTO();
		dto.setId(goods.getId());
		dto.setPrice(goods.getPrice());
		dto.setName(goods.getName());
		dto.setIsNew(goods.getIsNew());
		dto.setIsBest(goods.getIsBest());
		dto.setMain_thumb(goods.getMain_thumb());
		dto.setDetail(detail);
		
		return dto;
		 
	}
}
